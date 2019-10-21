package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTReturnStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTSoqlExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class Batch04 extends AbstractApexRule{
	@Override
	public Object visit(ASTUserClass node, Object data) {
		boolean isBatch = false;
		List<String> interfaceList = node.getInterfaceNames();
		
		for(String interfaceName : interfaceList) {
			if (interfaceName.toLowerCase().contains("batch")) {
				isBatch = true;
				break;
			}
		}
		
		if (!isBatch) {
			return data;
		}
		
		List<ASTSoqlExpression> soqlList = node.findDescendantsOfType(ASTSoqlExpression.class);
		if (!soqlList.isEmpty()) {
			for (ASTSoqlExpression soql : soqlList) {
				addViolation(data, soql);
			}
		}
		
		List<ASTMethodCallExpression> methodCallList = node.findDescendantsOfType(ASTMethodCallExpression.class);
		for (ASTMethodCallExpression methodCall : methodCallList) {
			if(methodCall.getFullMethodName().toLowerCase().contentEquals("database.query")) {
				addViolation(data, methodCall);
			}
		}
		
		ASTMethod startMethod = getMethodStart(node);
		
		if (startMethod != null) {
			ASTReturnStatement returnNode = startMethod.getFirstDescendantOfType(ASTReturnStatement.class);
			
			if (returnNode != null) {
				ASTMethodCallExpression returnMethod = returnNode.getFirstChildOfType(ASTMethodCallExpression.class);
				if (returnMethod == null) {
					addViolation(data, startMethod);
				}
			} else {
				addViolation(data, startMethod);
			}
		}
		
		ASTMethodCallExpression dbGetQueryLocatorNode = null;
		for (ASTMethodCallExpression methodCall : methodCallList) {
			if(methodCall.getFullMethodName().toLowerCase().contentEquals("database.getquerylocator")) {
				dbGetQueryLocatorNode = methodCall;
			}
		}
		ASTMethod method = dbGetQueryLocatorNode.getFirstParentOfType(ASTMethod.class);
			if (startMethod != null) {
				for (ASTMethodCallExpression mce : methodCallList) {
					if (mce.getFullMethodName().toLowerCase().contentEquals(method.getImage().toLowerCase())) {
						return data;
					}
				}
				addViolation(data, method);
			}
		
		return data;
	}
	
//	@Override
//	public Object visit(ASTMethodCallExpression node, Object data) {
//		ASTUserClass userClass = node.getFirstParentOfType(ASTUserClass.class);
//		
//		if (node.getFullMethodName().toLowerCase().contentEquals("database.getquerylocator")) {
//			ASTMethod method = node.getFirstParentOfType(ASTMethod.class);
//			
//			if (!method.getImage().toLowerCase().contentEquals("start")) {
//				ASTMethod startMethod = getMethodStart(userClass);
//				
//				if (startMethod != null) {
//					List<ASTMethodCallExpression> methodCallList = startMethod.findDescendantsOfType(ASTMethodCallExpression.class);
//					for (ASTMethodCallExpression mce : methodCallList) {
//						if (mce.getFullMethodName().toLowerCase().contentEquals(method.getImage().toLowerCase())) {
//							return data;
//						}
//					}
//					addViolation(data, method);
//				}
//			}
//		}
//		return data;
//	}
	
	private ASTMethod getMethodStart(ASTUserClass userClass) {
		List<ASTMethod> methodList = userClass.findChildrenOfType(ASTMethod.class);
		
		for (ASTMethod method : methodList) {
			if (method.getImage().toLowerCase().contentEquals("start")) {
				return method;
			}
		}
		return null;
	} 
}
