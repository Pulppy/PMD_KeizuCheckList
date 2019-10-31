package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTBlockStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTTryCatchFinallyBlockStatement;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

/*
* @created: Lu Chi Hao
*/

public class VisualPage18 extends AbstractApexRule {
	@Override
	public Object visit(ASTMethod node, Object data) {
		
		//Neu method co kieu tra ve la PageReference thi phai co try catch
		if(node.getReturnType().contentEquals("PageReference")) {
			ASTBlockStatement nodeJr = node.getFirstDescendantOfType(ASTBlockStatement.class);
			if(!(nodeJr.jjtGetChild(0) instanceof ASTTryCatchFinallyBlockStatement)) {
				addViolation(data, node);
			}
		}
		return data;
	}
}
