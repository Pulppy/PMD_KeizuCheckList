package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class VisualPage06 extends AbstractApexRule {
	@Override
	public Object visit(ASTMethodCallExpression node, Object data) {
		if(node.getFullMethodName().contentEquals("apexpages.addMessage")) {
			List<ASTVariableExpression> lst = node.findDescendantsOfType(ASTVariableExpression.class);
			for(ASTVariableExpression element : lst) {
				String severity = element.getImage();
				if(severity.contentEquals("INFO") || severity.contentEquals("WARNING") || severity.contentEquals("ERROR") || severity.contentEquals("CONFIRM")) {
					addViolation(data, element);
				}
			}
		}
		return data;
	}
}
