package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTLiteralExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General11 extends  AbstractApexRule {
	@Override
	public Object visit(ASTUserClass node, Object data) {
		List<ASTLiteralExpression> lst0 = node.findDescendantsOfType(ASTLiteralExpression.class);
		addViolationWithMessage(data, node, String.valueOf(lst0.size()));
		List<ASTLiteralExpression> lst1 = new ArrayList<>();
		List<ASTLiteralExpression> lst2 = new ArrayList<>();
		for(ASTLiteralExpression ele : lst0) {
			if(!ele.getFirstParentOfType(ASTMethod.class).getImage().contentEquals("<clinit>")) {
				if(ele.isString() && ele.getParentsOfType(ASTField.class).isEmpty()) {
					lst1.add(ele);
				}else if(!ele.isString() && ele.getParentsOfType(ASTField.class).isEmpty()){
					lst2.add(ele);
				}
			}
		}
		addViolationWithMessage(data, node, String.valueOf(lst1.size()));
		addViolationWithMessage(data, node, String.valueOf(lst2.size()));
		if(!lst1.isEmpty()) {
			HashMap<String, List<ASTLiteralExpression>> mapLit = createMap(lst1);
			addVio(mapLit, data);
		}
		if(!lst2.isEmpty()) {
			HashMap<String, List<ASTLiteralExpression>> mapLit = createMap(lst2);
			addVio(mapLit, data);
		}
		return data;
	}
	
	private HashMap<String, List<ASTLiteralExpression>> createMap(List<ASTLiteralExpression> lst){
		HashMap<String, List<ASTLiteralExpression>> mapLit = new HashMap<String, List<ASTLiteralExpression>>();
		for(ASTLiteralExpression ele : lst) {
			if(!mapLit.containsKey(ele.getImage())) {
				List<ASTLiteralExpression> listNew = new ArrayList<ASTLiteralExpression>();
				listNew.add(ele);
				mapLit.put(ele.getImage(), listNew);
			}else {
				mapLit.get(ele.getImage()).add(ele);
			}
		}
		return mapLit;
	}
	
	private void addVio(HashMap<String, List<ASTLiteralExpression>> mapLit, Object data) {
		for(String key : mapLit.keySet()) {
			if(mapLit.get(key).size() > 1) {
				List<ASTLiteralExpression> lst = mapLit.get(key);
				for(ASTLiteralExpression ele : lst) {
					addViolation(data, ele);
				}
			}
		}
		return;
	}
}
