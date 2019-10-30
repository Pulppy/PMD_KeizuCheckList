package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTNewObjectExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class VisualPage01 extends AbstractApexRule{
	@Override
	public Object visit(ASTMethodCallExpression node, Object data) {
		if(!node.getFullMethodName().toLowerCase().contentEquals("apexpages.addmessage")) {
			return data;
		}else {
			if(node.jjtGetChild(1) instanceof ASTNewObjectExpression) {
				ASTNewObjectExpression node1 = node.getFirstDescendantOfType(ASTNewObjectExpression.class);
				
			}
		}
		return data;
	}
}
