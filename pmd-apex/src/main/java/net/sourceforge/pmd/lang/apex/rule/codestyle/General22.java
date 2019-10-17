package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTForLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General22 extends AbstractApexRule{
	@Override
	public Object visit(ASTForLoopStatement node, Object data) {
		ASTVariableDeclaration nodeChild = node.getFirstDescendantOfType(ASTVariableDeclaration.class);
		if(nodeChild.getImage().length() > 1) {
			addViolation(data, node);
		}
		return data;
	}

}
