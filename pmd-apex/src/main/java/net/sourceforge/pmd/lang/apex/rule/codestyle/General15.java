package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTModifierNode;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.Node;

public class General15 extends AbstractApexRule {
	@Override
	public Object visit(ASTUserClass node, Object data) {
		int indexChild = 0;
		ASTModifierNode modifier;
		Node child;
		while(indexChild < node.jjtGetNumChildren()){
			child = node.jjtGetChild(indexChild);
			if(child instanceof ASTField) {
				modifier = child.getFirstChildOfType(ASTModifierNode.class);
				if(!modifier.isPrivate()) {
					addViolation(data, child);
				}
			}
			indexChild++;
		}
		return data;
	}
}
