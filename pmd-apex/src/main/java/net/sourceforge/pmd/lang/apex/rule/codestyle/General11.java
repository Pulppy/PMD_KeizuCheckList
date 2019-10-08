package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTAssignmentExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTModifierNode;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General11 extends  AbstractApexRule {
	@Override
	public Object visit(ASTField node, Object data) {
		ASTModifierNode modifier = node.getFirstChildOfType(ASTModifierNode.class);
		ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
		List<ASTAssignmentExpression> lst = nodeAncestor.findDescendantsOfType(ASTAssignmentExpression.class);
		for(ASTAssignmentExpression ele : lst) {
			ASTVariableExpression nodeToCheck = ele.getFirstChildOfType(ASTVariableExpression.class);
			if(nodeToCheck.getImage().contentEquals(node.getImage())) {
				return data;
			}
		}
		if(!modifier.isFinal()) {
			addViolation(data, node);
		}
		return data;
	}
}
