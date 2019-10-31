package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTReturnStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTSoqlExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
/*
 * @class		: Batch04
 * @created		: 2019/10/24 Truong Trang Ngoc Phuc
 * @description	: Trong truog hop xu ly du lieu lon bat buoc phai get du lieu trong Database.getQueryLocator de lay
 * @modified	:
 * */
public class Batch04 extends AbstractApexRule{
	@Override
	public Object visit(ASTUserClass node, Object data) {
		boolean isBatch = false;
		List<String> interfaceList = node.getInterfaceNames();
		// Kiem tra co phai Batch ko
		for(String interfaceName : interfaceList) {
			if (interfaceName.toLowerCase().contains("batch")) {
				isBatch = true;
				break;
			}
		}
		
		if (!isBatch) {
			return data;
		}
		// Tim nhung node Method Call trong User Class
		List<ASTMethodCallExpression> methodCallList = node.findDescendantsOfType(ASTMethodCallExpression.class);
		
		// Tim method start trong batch
		ASTMethod startMethod = getMethodStart(node);
		
		if (startMethod != null) {
			// Tim node return de kiem tra xem co phai tra ve QueryLocator hay ko
			ASTReturnStatement returnNode = startMethod.getFirstDescendantOfType(ASTReturnStatement.class);
			
			if (returnNode != null) {
				// Bat buoc tra ve la mot function
				// Database.queryLocator hoac mot ham tu ghi
				ASTMethodCallExpression returnMethod = returnNode.getFirstChildOfType(ASTMethodCallExpression.class);
				if (returnMethod == null) {
					addViolation(data, startMethod);
				}
			} else {
				// Neu ko co return thi bao loi
				addViolation(data, startMethod);
			}
		}
		
		// Tim Database.queryLocator trong toan bo User Class
		List<ASTMethodCallExpression> dbGetQueryLocatorList = new ArrayList();
		for (ASTMethodCallExpression methodCall : methodCallList) {
			if(methodCall.getFullMethodName().toLowerCase().contentEquals("database.getquerylocator")) {
				dbGetQueryLocatorList.add(methodCall);
			}
		}
		
		// Neu ko co Database.queryLocator thi vang loi va ket thuc
		if (dbGetQueryLocatorList.isEmpty()) {
			addViolation(data, startMethod);
			return data;
		}
		for (ASTMethodCallExpression dbGetQueryLocatorNode : dbGetQueryLocatorList) {
			// Tim method chua Database.queryLocator
			ASTMethod method = dbGetQueryLocatorNode.getFirstParentOfType(ASTMethod.class);
			// Neu method nay duoc goi ngoai ham start thi bao loi
			for (ASTMethodCallExpression mce : methodCallList) {
				if (mce.getFullMethodName().contentEquals(method.getImage())) {
					ASTMethod parMethod = mce.getFirstParentOfType(ASTMethod.class);
					if (!parMethod.getImage().toLowerCase().contentEquals("start")) {
						addViolation(data, mce);
					}
				}
			}
			// Tim nhung method Call trong ham start
			List<ASTMethodCallExpression> startMethodCallList = startMethod.findDescendantsOfType(ASTMethodCallExpression.class);
				if (startMethod != null) {
					ASTReturnStatement returnNode = startMethod.getFirstDescendantOfType(ASTReturnStatement.class);
					ASTMethodCallExpression returnMethod = null;
					if (returnNode != null) {
						returnMethod = returnNode.getFirstChildOfType(ASTMethodCallExpression.class);
						// Neu start return dung voi ten ham chua Database.getQueryLocator thi ket thuc
						if (returnMethod.getFullMethodName().contentEquals(method.getImage())) {
							continue;
						}
						// Neu Database.getQueryLocator nam trong ham start thi ket thuc
						if (method.getImage().toLowerCase().contentEquals("start")) {
							continue;
						}
						// Neu ko roi vao 2 truong hop tren thi bao loi
						addViolation(data, method);
					}
				}
		}
		
		return data;
	}


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
