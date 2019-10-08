package net.sourceforge.pmd.lang.apex.rule.codestyle;


import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

import java.util.ArrayList;
import java.util.List;

public class General19 extends AbstractApexRule{
	@Override
	public Object visit(ASTField node, Object data) {
		Integer count = 0;
		ASTUserClass nodeFather = node.getFirstParentOfType(ASTUserClass.class);
		List<ASTMethod> lst = nodeFather.findDescendantsOfType(ASTMethod.class);
		for(ASTMethod ele : lst) {
			if(!ele.getImage().contentEquals("<clinit>")) {
				List<ASTVariableExpression> lstJr = ele.findDescendantsOfType(ASTVariableExpression.class);
				if(lstJr != null && lstJr.size() > 0) {
					List<ASTVariableExpression> lstGrandJr = new ArrayList<>();
					for(ASTVariableExpression eleJr : lstJr) {
						if(eleJr.getImage().contentEquals(node.getImage())) {
							lstGrandJr.add(eleJr);
						}
					}
					if(lstGrandJr != null && lstGrandJr.size() > 0) {
						count = count + 1;
					}
				}
			}
		}
		if(count <= 1) {
			addViolation(data, node);
		}
		return data;
	}
}
	