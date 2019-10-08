package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General33 extends AbstractApexRule{
	@Override
	public Object visit(ASTMethodCallExpression node, Object data) {
		if(node.getFullMethodName().contentEquals("System.debug") || node.getFullMethodName().contentEquals("system.debug")) {
			addViolation(data, node);
		}
		return data;
	}
}
