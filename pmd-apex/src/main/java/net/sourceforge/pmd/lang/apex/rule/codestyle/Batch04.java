package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class Batch04 extends AbstractApexRule{
	@Override
	public Object visit(ASTMethodCallExpression node, Object data) {
		ASTUserClass userClass = node.getFirstParentOfType(ASTUserClass.class);

		if (node.getFullMethodName().toLowerCase().contentEquals("database.getquerylocator")) {
			ASTMethod method = node.getFirstParentOfType(ASTMethod.class);
			
			if (!method.getImage().toLowerCase().contentEquals("start")) {
				ASTMethod startMethod = getMethodStart(userClass);
				
				if (startMethod != null) {
					List<ASTMethodCallExpression> methodCallList = startMethod.findDescendantsOfType(ASTMethodCallExpression.class);
					for (ASTMethodCallExpression mce : methodCallList) {
						if (mce.getFullMethodName().toLowerCase().contentEquals(method.getImage().toLowerCase())) {
							return data;
						}
					}
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
