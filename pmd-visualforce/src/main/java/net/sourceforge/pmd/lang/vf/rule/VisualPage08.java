package net.sourceforge.pmd.lang.vf.rule;

import java.util.List;

import net.sourceforge.pmd.lang.vf.ast.ASTElement;

public class VisualPage08 extends AbstractVfRule {
	@Override
	public Object visit(ASTElement node, Object data) {
		// Tim kiem cac node con
		List<ASTElement> childNodeList = node.findDescendantsOfType(ASTElement.class);
		Integer noTagForm = 0;	// So the apex:form
		for (ASTElement child : childNodeList) {
			if (child.getName().toLowerCase().contentEquals("apex:form")) {
				noTagForm++;
			}
			if (noTagForm > 1) {
				addViolation(data, child);
			}
		}

		return data;
	}
}
