package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTAssignmentExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTLiteralExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTNewObjectExpression;
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
		List<ASTLiteralExpression> litExpList = node.findDescendantsOfType(ASTLiteralExpression.class);
		if (!litExpList.isEmpty()) {
			for (ASTLiteralExpression litExp : litExpList) {
				// Truong hop la hard-code trong new PageReference
				ASTNewObjectExpression parentNewObj = litExp.getFirstParentOfType(ASTNewObjectExpression.class);
				if (parentNewObj != null) {
					if (parentNewObj.getType().toLowerCase().contentEquals("pagereference")) {
						addViolation(data, parentNewObj);
					}
				}
			}
			
			List<ASTNewObjectExpression> newObjList = node.findDescendantsOfType(ASTNewObjectExpression.class);
			if (!newObjList.isEmpty()) {
				for (ASTNewObjectExpression newObj : newObjList) {
					if (newObj.getType().toLowerCase().contentEquals("pagereference")) {
						ASTVariableExpression childVarExp = newObj.getFirstChildOfType(ASTVariableExpression.class);
						if (childVarExp != null) {
							String varName = childVarExp.getImage();
							ASTMethod parentMethod = newObj.getFirstParentOfType(ASTMethod.class);
							List<ASTVariableDeclaration> varDecList = parentMethod.findDescendantsOfType(ASTVariableDeclaration.class);
							List<ASTAssignmentExpression> assExpList = parentMethod.findDescendantsOfType(ASTAssignmentExpression.class);
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
									addViolation(data, newObj);
									
								}
							}
							
							if (!assExpList.isEmpty()) {
								ASTVariableExpression variableExpression = null;
								ASTLiteralExpression literalExpression = null;
								
								for (ASTAssignmentExpression assExp :  assExpList) {
									ASTVariableExpression varExp = assExp.getFirstChildOfType(ASTVariableExpression.class);
									ASTLiteralExpression litExp = assExp.getFirstChildOfType(ASTLiteralExpression.class);
									
									if (varExp != null) {
										if (varExp.getImage().contentEquals(varName) && varExp.getBeginLine() < newObj.getBeginLine()) {
											variableExpression = varExp;
											literalExpression = litExp;
										}
									}
								}
								
								if (variableExpression != null && literalExpression != null) {
									addViolation(data, newObj);
								}
							}
						}
					}
				}
			}
		}
		return data;
	}
}
