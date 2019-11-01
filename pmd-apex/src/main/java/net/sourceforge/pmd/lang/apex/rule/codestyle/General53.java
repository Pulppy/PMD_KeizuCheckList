package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTAssignmentExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTLiteralExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTNewObjectExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTReturnStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
/*
 * Class		: General53
 * @created		: 2019/10/25 Truong Trang Ngoc Phuc
 * @discription	: Trong source controller, test class, page KO HARDCODE page apex/confirm ma thay the bang cach Page.Confirm
 * @modified	:
 */
public class General53 extends AbstractApexRule {
	@Override
	public Object visit(ASTMethod node, Object data) {
		List<ASTReturnStatement> returnStmList = new ArrayList();
		if (node.getReturnType().toLowerCase().contentEquals("pagereference")) {
			returnStmList = node.findDescendantsOfType(ASTReturnStatement.class);
			if (returnStmList.isEmpty()) {
				addViolation(data, node, "line 27");
			}
		} else {
			return data;
		}
		
		List<ASTLiteralExpression> litExpList = node.findDescendantsOfType(ASTLiteralExpression.class);
		if (!litExpList.isEmpty()) {
			for (ASTLiteralExpression litExp : litExpList) {
				// Truong hop la hard-code trong new PageReference
				ASTNewObjectExpression parentNewObj = litExp.getFirstParentOfType(ASTNewObjectExpression.class);
				if (parentNewObj != null) {
					if (parentNewObj.getType().toLowerCase().contentEquals("pagereference")) {
						addViolation(data, parentNewObj, "line 40");
						ASTVariableDeclaration parentVarDec = parentNewObj.getFirstParentOfType(ASTVariableDeclaration.class);
						if(parentVarDec != null) {
//							addViolation(data, parentVarDec, "line 43" + parentVarDec.getImage());// parentVarDec
							// 2029/11/01 start
							// Kiem tra return co tra ve cai bien hard code ko
							String variableName_Direct = "";
							for (ASTReturnStatement returnStm : returnStmList) {
								ASTVariableExpression returnVarExp = returnStm.getFirstChildOfType(ASTVariableExpression.class);
//									addViolationWithMessage(data, returnStm, returnVarExp.getImage() + " -line 94- " + varExp.getImage());
								if (returnVarExp != null) {
									if (returnVarExp.getImage().contentEquals(parentVarDec.getImage()) 
											&& !parentVarDec.getImage().contentEquals(variableName_Direct)) {
//										addViolationWithMessage(data, returnStm, returnVarExp.getImage() + " --- " + variableName_NonDirect);
										addViolation(data, returnStm);
										variableName_Direct = returnVarExp.getImage();
									}
								}
							}
							// 2029/11/01 end
						}
					}
				}
			}
		}
		List<ASTNewObjectExpression> newObjList = node.findDescendantsOfType(ASTNewObjectExpression.class);
		if (!newObjList.isEmpty()) {
			// ten bien tranh bao loi nhieu lan cung 1 node
			String variableName_NonDirect = "";
			String variableName_Direct = "";
			for (ASTNewObjectExpression newObj : newObjList) {
				if (newObj.getType().toLowerCase().contentEquals("pagereference")) {
					ASTVariableExpression childVarExp = newObj.getFirstChildOfType(ASTVariableExpression.class);
					
					if (childVarExp != null) {
						String varName = childVarExp.getImage();
//							addViolationWithMessage(data, childVarExp, "childVarExp: " + varName);
						List<ASTVariableDeclaration> varDecList = node.findDescendantsOfType(ASTVariableDeclaration.class);
						List<ASTAssignmentExpression> assExpList = node.findDescendantsOfType(ASTAssignmentExpression.class);
						if (!varDecList.isEmpty()) {
							ASTVariableDeclaration variableDeclaration = null;
							for (ASTVariableDeclaration varDec : varDecList) {
								if(varDec.getImage().contentEquals(varName) && varDec.getBeginLine() < newObj.getBeginLine()) {
									if (varDec.getFirstChildOfType(ASTLiteralExpression.class) != null) {
										variableDeclaration = varDec;
									}
								}
							}
							
							if (variableDeclaration != null) {
								addViolation(data, newObj, "line 71");
							}
							
						}
						
						
						if (!assExpList.isEmpty()) {
							ASTVariableExpression variableExpression = null;
							ASTLiteralExpression literalExpression = null;
							String variableExpressionName = "";
							for (ASTAssignmentExpression assExp :  assExpList) {
								ASTVariableExpression varExp = assExp.getFirstChildOfType(ASTVariableExpression.class);
								ASTLiteralExpression litExp = assExp.getFirstChildOfType(ASTLiteralExpression.class);
								
								if (varExp != null) {
									if (varExp.getImage().contentEquals(varName) && varExp.getBeginLine() < newObj.getBeginLine()) {
										variableExpression = varExp;
										literalExpression = litExp;
										// 2029/11/01 start
										ASTVariableDeclaration varDec = newObj.getFirstParentOfType(ASTVariableDeclaration.class);
										if (varDec != null) {
//											addViolationWithMessage(data, varDec, "line 111 " +  varDec.getImage());
											// Kiem tra return co tra ve cai bien hard code ko
											String variableName = "";
											for (ASTReturnStatement returnStm : returnStmList) {
												ASTVariableExpression returnVarExp = returnStm.getFirstChildOfType(ASTVariableExpression.class);
//													addViolationWithMessage(data, returnStm, returnVarExp.getImage() + " -line 94- " + varExp.getImage());
												if (returnVarExp != null) {
													if (returnVarExp.getImage().contentEquals(varDec.getImage()) 
															&& !varExp.getImage().contentEquals(variableName)) {
//														addViolationWithMessage(data, returnStm, returnVarExp.getImage() + " --- " + variableName_NonDirect);
														addViolation(data, returnStm);
														variableName = returnVarExp.getImage();
													}
												}
										}
										// 2019/11/01 end
									}
								}
								// 2029/11/01 start
								// Kiem tra return co tra ve cai bien hard code ko
								for (ASTReturnStatement returnStm : returnStmList) {
									ASTVariableExpression returnVarExp = returnStm.getFirstChildOfType(ASTVariableExpression.class);
//										addViolationWithMessage(data, returnStm, returnVarExp.getImage() + " -line 94- " + varExp.getImage());
									if (returnVarExp != null) {
										if (returnVarExp.getImage().contentEquals(varExp.getImage()) 
												&& !varExp.getImage().contentEquals(variableName_NonDirect)) {
//											addViolationWithMessage(data, returnStm, returnVarExp.getImage() + " --- " + variableName_NonDirect);
											addViolation(data, returnStm);
											variableName_NonDirect = returnVarExp.getImage();
										}
									}
								}
								// 2029/11/01 end
							}
							
							if (variableExpression != null && literalExpression != null && !variableExpression.hasImageEqualTo(variableExpressionName)) {
								addViolation(data, newObj, "line 107" + variableExpression.getImage());
								variableExpressionName = variableExpression.getImage();
							}
						}
					}
//					// 2019/11/01 start
//					ASTVariableDeclaration parentVarDec = newObj.getFirstParentOfType(ASTVariableDeclaration.class);
//					if (parentVarDec != null) {
//						addViolationWithMessage(data, newObj, "line 78: " + parentVarDec.getImage());
//					} else {
//						addViolationWithMessage(data, newObj, "line 78 null");
//					}
//					// 2019/11/01 end
				}
			}
		}
		}
		return data;
	}
}
