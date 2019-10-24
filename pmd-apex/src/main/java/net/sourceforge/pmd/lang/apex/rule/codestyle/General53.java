package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTNewObjectExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTReturnStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General53 extends AbstractApexRule {
	private static final String GET_CURRENT_PAGE = "apexPage.getcurrentpage";
	private static final String GET_PARAMETER = "getparameters.get";
	
	@Override
	public Object visit(ASTMethod node, Object data) {
		if(node.getReturnType().toLowerCase().contentEquals("pagereference")) {
			List<ASTReturnStatement> returnStmList = node.findChildrenOfType(ASTReturnStatement.class);
			for (ASTReturnStatement returnStm : returnStmList) {
				ASTNewObjectExpression newObjExpression = returnStm.getFirstChildOfType(ASTNewObjectExpression.class);
				ASTVariableExpression varExpression = returnStm.getFirstChildOfType(ASTVariableExpression.class);
				
				if(newObjExpression != null) {
					if(newObjExpression.getType().toLowerCase().contentEquals("pagereference")) {
						addViolation(data, node);
					}
				}
				if(varExpression != null) {
					List<ASTVariableDeclaration> varExpList = node.findDescendantsOfType(ASTVariableDeclaration.class);
					for(ASTVariableDeclaration varExp : varExpList) {
						if(varExp.getImage().contentEquals(varExpression.getImage()) 
						 && varExp.getType().toLowerCase().contentEquals("pagereference")) {
							addViolation(data, varExpression);
						}
					}
				}
			}
		}
		return data;
	}
	@Override
	public Object visit(ASTMethodCallExpression node, Object data) {
		String methodName = node.getFullMethodName().toLowerCase();
		if (methodName.toLowerCase().contains(GET_CURRENT_PAGE) || methodName.toLowerCase().contains(GET_PARAMETER)) {
			addViolation(data, node);
		}
		
		return data;
	}
}
