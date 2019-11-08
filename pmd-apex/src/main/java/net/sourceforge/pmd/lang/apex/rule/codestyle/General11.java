package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTLiteralExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclarationStatements;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

/*
* @created	: Lu Chi Hao
* @modified	: 2019/11/08 Truong Trang Ngoc Phuc
*/

public class General11 extends  AbstractApexRule {
	// List luu nhung line da add loi
	private static List<Integer> errLineList = new ArrayList() {
		{
			add(-1);
		}
	};
	@Override
	public Object visit(ASTUserClass node, Object data) {
		List<ASTLiteralExpression> lst0 = node.findDescendantsOfType(ASTLiteralExpression.class);
		List<ASTLiteralExpression> lst1 = new ArrayList<>();
		List<ASTLiteralExpression> lst2 = new ArrayList<>();
		for(ASTLiteralExpression ele : lst0) {
			if(!ele.getFirstParentOfType(ASTMethod.class).getImage().contentEquals("<clinit>")) {
				if(ele.isString() && ele.getParentsOfType(ASTField.class).isEmpty()
						&& !ele.getImage().contentEquals("") && ele.getFirstParentOfType(ASTVariableDeclarationStatements.class) == null) {
					lst1.add(ele);
				}else if(!ele.isString() && ele.getParentsOfType(ASTField.class).isEmpty() && !ele.isNull()
						&& !ele.isBoolean()	&& !ele.getImage().contentEquals("0") && ele.getFirstParentOfType(ASTVariableDeclarationStatements.class) == null){
					lst2.add(ele);
				}
			}
		}

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
				// 2019/11/08 Truong Trang Ngoc Phuc modify start	
//					addViolation(data, ele);
					// flag kiem tra line hien tai da co add loi chua
					boolean thisLineHasError = false;
					//  Neu line hien tai da dc add error thi bat co thisLineHasError
					for (Integer line : errLineList) {
						if (ele.getBeginLine() == line) {
							thisLineHasError = true;
							break;
						}
					}
					// Neu line hien tai chua dc add error thi
					// add error vao node do va luu line hien tai vao List errLineList
					if (!thisLineHasError) {
						addViolation(data, ele);
						errLineList.add(ele.getBeginLine());
					}
				// 2019/11/08 Truong Trang Ngoc Phuc modify end
				}
			}
		}
		return;
	}
}
