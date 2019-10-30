package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTForLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General22 extends AbstractApexRule{
	@Override
	public Object visit(ASTForLoopStatement node, Object data) {
		ASTVariableDeclaration nodeChild = node.getFirstDescendantOfType(ASTVariableDeclaration.class);
		
		//Xet neu bien co dai hon 1 ky tu hay khong
		if(nodeChild.getImage().length() > 1) {
			addViolation(data, node);
		}
		return data;
	}

}
