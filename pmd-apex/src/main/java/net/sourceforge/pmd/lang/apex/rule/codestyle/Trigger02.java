package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTFieldDeclaration;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTNewObjectExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTReferenceExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserTrigger;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

/*
* @created: Lu Chi Hao
*/

public class Trigger02 extends AbstractApexRule {
	@Override
	public Object visit(ASTUserTrigger node, Object data) {
		List<ASTField> lstField = node.findDescendantsOfType(ASTField.class);
		// Lay ten cua trigger class
		String className = node.getImage();
		// Ten cua handler phai la ten trigger cong voi chu Handler
		String handlerName = className + "Handler";
		if (lstField.isEmpty()) {
			addViolation(data, node);
			return data;
		}
		ASTField element = null;
		for (Integer i = 0; i <= lstField.size(); i++) {
			if (i == lstField.size()) {
				addViolation(data, node);
			} else {
				if (lstField.get(i).getType().contentEquals(handlerName)) {
					element = lstField.get(i);
					break;
				}
			}
		}
		List<ASTFieldDeclaration> lstFieldDeclaration = node.findDescendantsOfType(ASTFieldDeclaration.class);
		for (ASTFieldDeclaration element1 : lstFieldDeclaration) {
			if (element1.getImage().contentEquals(element.getImage())) {
				if (!element1.hasDescendantOfType(ASTNewObjectExpression.class)) {
					addViolation(data, element);
					return data;
				}
			}
		}

		// Tim tat ca cac method duoc call trong class
		List<ASTMethodCallExpression> lstMethodCallExpression = node
				.findDescendantsOfType(ASTMethodCallExpression.class);
		ASTMethodCallExpression element1;
		Boolean handlerUsed = false;
		for (Integer i = 0; i < lstMethodCallExpression.size(); i++) {

			// Neu method duoc call khong nam trong lop handler thi bao loi
			element1 = lstMethodCallExpression.get(i);
			if (!element1.hasDescendantOfType(ASTReferenceExpression.class)) {
				addViolation(data, element1);
			}
		}
		return data;
	}
}
