package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTAnnotation;
import net.sourceforge.pmd.lang.apex.ast.ASTForLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General24 extends AbstractApexRule {
	@Override
	public Object visit(ASTForLoopStatement node, Object data) {
		List<ASTMethodCallExpression> lstToCheck = node.findDescendantsOfType(ASTMethodCallExpression.class);
		for(ASTMethodCallExpression element : lstToCheck) {
			List<ASTUserClass> nodeRoot = node.getParentsOfType(ASTUserClass.class);
			List<ASTMethod> lstMethod = nodeRoot.get(0).findDescendantsOfType(ASTMethod.class);
			for(ASTMethod element1 : lstMethod) {
				if(element1.getImage().contentEquals(element.getFullMethodName())) {
					if(element1.hasDescendantOfType(ASTAnnotation.class)) {
						ASTAnnotation methodCheck = element1.getFirstDescendantOfType(ASTAnnotation.class);
						if(methodCheck.getImage().contentEquals("Future")) {
							addViolation(data, element);
							break;
						}
					}
				}
			}
		}
		return data;
	}
}
