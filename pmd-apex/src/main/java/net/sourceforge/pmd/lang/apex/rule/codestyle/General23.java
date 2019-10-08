package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTBooleanExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTForLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTLiteralExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTStandardCondition;
import net.sourceforge.pmd.lang.apex.ast.ASTWhileLoopStatement;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General23 extends AbstractApexRule{
	@Override
	public Object visit(ASTForLoopStatement node, Object data) {
		ASTStandardCondition loopInit = node.getFirstChildOfType(ASTStandardCondition.class);
		ASTBooleanExpression loopCount =  loopInit.getFirstChildOfType(ASTBooleanExpression.class);
		int index = 0;
		while(index < loopCount.jjtGetNumChildren()){
			if(loopCount.jjtGetChild(index) instanceof ASTLiteralExpression) {
				addViolation(data, loopCount);
				break;
			}
			index++;
		}
		return data;
	}
	
	@Override
	public Object visit(ASTWhileLoopStatement node, Object data) {
		ASTStandardCondition loopInit = node.getFirstChildOfType(ASTStandardCondition.class);
		ASTBooleanExpression loopCount =  loopInit.getFirstChildOfType(ASTBooleanExpression.class);
		int index = 0;
		while(index < loopCount.jjtGetNumChildren()){
			if(loopCount.jjtGetChild(index) instanceof ASTLiteralExpression) {
				addViolation(data, loopCount);
				break;
			}
			index++;
		}
		return data;
	}
}
