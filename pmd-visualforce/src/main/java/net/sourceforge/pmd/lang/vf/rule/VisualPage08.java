package net.sourceforge.pmd.lang.vf.rule;

import java.util.List;

import net.sourceforge.pmd.lang.vf.ast.ASTElement;
/*
 * Class		: VisualPage08
 * @Created		: 2019/10/28 Truong Trang Ngoc Phuc
 * @Description	: Khai bao 1 page, 1 form tuong ung. Ko khai bao 2 form cho 1 page
 * @Modified		:
 * */
public class VisualPage08 extends AbstractVfRule {
	@Override
	public Object visit(ASTElement node, Object data) {
		if (!node.getName().toLowerCase().contentEquals("apex:page")) {
			return data;
		}
		// Tim kiem cac node con
		List<ASTElement> childNodeList = node.findDescendantsOfType(ASTElement.class);
		// So the apex:form
		Integer noTagForm = 0;
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
