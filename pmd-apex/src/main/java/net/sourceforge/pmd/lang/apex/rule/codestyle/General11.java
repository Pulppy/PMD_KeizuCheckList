package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTModifierNode;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General11 extends  AbstractApexRule {
	@Override
	public Object visit(ASTField node, Object data) {
		ASTModifierNode modifier = node.getFirstChildOfType(ASTModifierNode.class);
		if(!modifier.isStatic()) {
			addViolation(data, node);
		}
		return data;
	}
}
