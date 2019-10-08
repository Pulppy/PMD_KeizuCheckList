package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTAssignmentExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTExpressionStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTReferenceExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
/* @class: MethodCallMstHvCommaAndSpaceBtw
 * 
 * @created: KSVC Truong Trang Ngoc Phuc
 * @date created: 2019/09/30
 * 
 * 
 */
public class General18 extends AbstractApexRule{
	@Override
	public Object visit(ASTMethodCallExpression node, Object data ) {
		List<ASTExpressionStatement> expStmtList = node.getParentsOfType(ASTExpressionStatement.class);
		ASTExpressionStatement expStmt = null;
		Integer beginCol = 0;
		Integer endCol = 0;
		boolean isSingleLine = false;
		Integer countCol;
		
		if (!node.findChildrenOfType(ASTMethodCallExpression.class).isEmpty()) {
			return data;
		}
		
		if (!node.findChildrenOfType(ASTReferenceExpression.class).isEmpty() ) {
			return data;
		}
		
		if (node.getFirstParentOfType(ASTAssignmentExpression.class) != null) {
			return data;
		}
		
		if(!expStmtList.isEmpty()) {
			expStmt = expStmtList.get(0);
			isSingleLine = expStmt.isSingleLine();
			beginCol = expStmt.getBeginColumn();
			endCol = expStmt.getEndColumn();
		}
		countCol = beginCol;
		
		if(expStmt != null && isSingleLine) {
			List<String> paramNameList = new ArrayList<String>();
			String methodName = node.getFullMethodName();
			List<ASTVariableExpression> varExpList = node.findChildrenOfType(ASTVariableExpression.class);
			if (!varExpList.isEmpty()) {
				for(ASTVariableExpression varExp : varExpList) {
					paramNameList.add(varExp.getImage());
				}
			}
			
			countCol += methodName.length();
			if (!paramNameList.isEmpty()) {
				for (String paramName : paramNameList) {
					countCol += paramName.length();
				}
			} else {
				return data;
			}
			
			if (!paramNameList.isEmpty()) {
				countCol += (paramNameList.size() - 1) * 2;	// ' ' + ','
			}
			countCol += 2;	// '(' + ')'
			
			if (countCol != endCol) {
				addViolation(data, node, "");
			}
		}
		
		return data;
	}
	
	
	
}
