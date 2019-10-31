package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General53 extends AbstractApexRule {
	private static final String GET_CURRENT_PAGE = "apexPage.getcurrentPage";
	private static final String GET_PARAMETER = "getparameters.get";
	
	@Override
	public Object visit(ASTMethodCallExpression node, Object data) {
		String methodName = node.getFullMethodName().toLowerCase();
		if (methodName.contains(GET_CURRENT_PAGE) || methodName.contains(GET_PARAMETER)) {
			addViolation(data, node);
		}
		
		return data;
	}
}
