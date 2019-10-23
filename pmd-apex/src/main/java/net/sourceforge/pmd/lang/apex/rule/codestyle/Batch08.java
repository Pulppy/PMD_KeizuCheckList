package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class Batch08 extends AbstractApexRule{
	private static final String STR_BATCH = "Batch";
	private static final String SYSTEM_SCHEDULE = "system.schedule";
	private static final int MAX_NO_SCHEDULE = 2;
	@Override
	public Object visit(ASTUserClass node, Object data) {
		List<String> interfaceNameList = node.getInterfaceNames();
		boolean isBatch = false;
		int noSchedule = 0;
		if (!interfaceNameList.isEmpty()) {
			for (String intf : interfaceNameList) {
				if (intf.contains(STR_BATCH)) {
					isBatch = true;
					break;
				}
			}
		}
		
		if (isBatch) {
			List<ASTMethodCallExpression> methodCallList = node.findDescendantsOfType(ASTMethodCallExpression.class);
			for (ASTMethodCallExpression methodCall : methodCallList) {
				if (methodCall.getFullMethodName().equalsIgnoreCase(SYSTEM_SCHEDULE)) {
					noSchedule++;
				}
				if (noSchedule > MAX_NO_SCHEDULE) {
					addViolation(data, methodCall);	
				}
			}
		}
		
		return data;
	}
}
