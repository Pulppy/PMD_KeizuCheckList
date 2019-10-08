package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTFieldDeclaration;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTNewObjectExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTReferenceExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserTrigger;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class Trigger02 extends AbstractApexRule {
	@Override
	public Object visit(ASTUserTrigger node, Object data) {
		List<ASTField> lstField = node.findDescendantsOfType(ASTField.class);
		String className = node.getImage();
		String handlerName = className + "Handler";
		for(ASTField element : lstField) {
			if(element.getType().contentEquals(handlerName)) {
				List<ASTFieldDeclaration> lstFieldDeclaration = node.findDescendantsOfType(ASTFieldDeclaration.class);
				for(ASTFieldDeclaration element1 : lstFieldDeclaration) {
					if(element1.getImage().contentEquals(element.getImage())) {
						if(!element1.hasDescendantOfType(ASTNewObjectExpression.class)) {
							addViolation(data, element);
							return data;
						}
					}
				}
				List<ASTMethodCallExpression> lstMethodCallExpression = node.findDescendantsOfType(ASTMethodCallExpression.class);
				ASTMethodCallExpression element1;
				Boolean handlerUsed = false;
				for(Integer i = 0; i <= lstMethodCallExpression.size(); i++) {
					if(i == lstMethodCallExpression.size()) {
						addViolation(data, node);
						break;
					}
					element1 = lstMethodCallExpression.get(i);
					if(!element1.hasDescendantOfType(ASTReferenceExpression.class)) {
						addViolation(data, element1);						
					}else if(element1.getFirstChildOfType(ASTReferenceExpression.class).getImage().contentEquals(element.getImage())) {
						handlerUsed = true;
						if(i == lstMethodCallExpression.size() - 1) {
							break;
						}
					}else if(element1.getFirstChildOfType(ASTReferenceExpression.class).getImage().contentEquals("System")) {
						if(i == lstMethodCallExpression.size() - 1) {
							if(handlerUsed) {
								break;
							}
						}
					}
				}
				return data;
			}
		}
		addViolation(data, node);
		return data;
	}
}
