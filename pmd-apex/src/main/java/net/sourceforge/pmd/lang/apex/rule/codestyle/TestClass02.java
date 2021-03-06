package net.sourceforge.pmd.lang.apex.rule.codestyle;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTModifierNode;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
/*
 * @class		: TestClass02
 * @created		: 2019/09/30 Truong Trang Ngoc Phuc
 * @discription	: Phai su dung method Test.startTest(), Test.testStop de giam governor limits khi thuc thi test
 * @modified	:
 */
public class TestClass02 extends AbstractApexRule {
	private static final String START_TEST = "Test.startTest";
	private static final String STOP_TEST = "Test.stopTest";
	@Override
	public Object visit(ASTMethod node, Object data) {
		ASTModifierNode modNode = node.getModifiers();
		
		if (modNode.isTest() && modNode.isTestOrTestSetup()) {
			boolean hasStartTest = false;
			boolean hasStopTest = false;
			List<ASTMethodCallExpression> methodCallList = node.findDescendantsOfType(ASTMethodCallExpression.class);
			for(ASTMethodCallExpression mce : methodCallList) {
				if (!hasStartTest && mce.getFullMethodName().contentEquals(START_TEST)) {
					hasStartTest = true;
				}
				
				if (!hasStopTest && mce.getFullMethodName().contentEquals(STOP_TEST)) {
					hasStopTest = true;
				}
				
				if (hasStartTest && hasStopTest) {
					return data;
				}
			}
			addViolation(data, node);
		}
		
		return data;
	}
}
