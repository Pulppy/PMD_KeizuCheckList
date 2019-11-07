package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
/*
 * Batch08
 * @created 		: 2019/10/31 Truong Trang Ngoc Phuc
 * @discription		: Trong batch ko the dang ki dc nhieu schedule chu y
 * @modified		:
 */
public class Batch08 extends AbstractApexRule{
	private static final String STR_BATCH = "Batch";
	private static final String SYSTEM_SCHEDULE = "system.schedule";
	private static final int MAX_NO_SCHEDULE = 1;
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
				if (methodCall.getFullMethodName().toLowerCase().contentEquals(SYSTEM_SCHEDULE)) {
					noSchedule++;
					if (noSchedule > MAX_NO_SCHEDULE) {
						addViolation(data, methodCall);	
					}
				}
				
			}
		}
		
		return data;
	}
}
