package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTDmlDeleteStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlInsertStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlUpdateStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlUpsertStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTIfBlockStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTStandardCondition;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General41 extends AbstractApexRule{
	public final String IS_EMTY = ".isEmty";
	public final String IS_BLANK = ".isBlank";
	@Override
	public Object visit(ASTDmlUpdateStatement node, Object data) {
		String variableName = node.getFirstDescendantOfType(ASTVariableExpression.class).getImage();
		ASTIfBlockStatement ifNode = node.getFirstParentOfType(ASTIfBlockStatement.class);
		if(ifNode == null) {
			addViolation(data, node);
		}
		ASTStandardCondition conditionNode = ifNode.getFirstDescendantOfType(ASTStandardCondition.class);
		List<ASTMethodCallExpression> lstMethodCall = conditionNode.findDescendantsOfType(ASTMethodCallExpression.class);
		if(!lstMethodCall.isEmpty()){
			List<String> lstMethodCallName = new ArrayList<>();
			for(ASTMethodCallExpression ele : lstMethodCall) {
				lstMethodCallName.add(ele.getFullMethodName());
			}
			for(Integer i = 0; i <= lstMethodCallName.size(); i++) {
				if(i == lstMethodCallName.size()) {
					addViolation(data, node);
				}else {
					if(lstMethodCallName.get(i).contentEquals(variableName + IS_EMTY) || lstMethodCallName.get(i).contentEquals(variableName + IS_BLANK)) {
						break;
					}
				}
			}
		}else {
			addViolation(data, node);
		}
		return data;
	}
	
	@Override
	public Object visit(ASTDmlDeleteStatement node, Object data) {
		String variableName = node.getFirstDescendantOfType(ASTVariableExpression.class).getImage();
		ASTIfBlockStatement ifNode = node.getFirstParentOfType(ASTIfBlockStatement.class);
		if(ifNode == null) {
			addViolation(data, node);
		}
		ASTStandardCondition conditionNode = ifNode.getFirstDescendantOfType(ASTStandardCondition.class);
		List<ASTMethodCallExpression> lstMethodCall = conditionNode.findDescendantsOfType(ASTMethodCallExpression.class);
		if(!lstMethodCall.isEmpty()){
			List<String> lstMethodCallName = new ArrayList<>();
			for(ASTMethodCallExpression ele : lstMethodCall) {
				lstMethodCallName.add(ele.getFullMethodName());
			}
			for(Integer i = 0; i <= lstMethodCallName.size(); i++) {
				if(i == lstMethodCallName.size()) {
					addViolation(data, node);
				}else {
					if(lstMethodCallName.get(i).contentEquals(variableName + IS_EMTY) || lstMethodCallName.get(i).contentEquals(variableName + IS_BLANK)) {
						break;
					}
				}
			}
		}else {
			addViolation(data, node);
		}
		return data;
	}
	
	@Override
	public Object visit(ASTDmlInsertStatement node, Object data) {
		String variableName = node.getFirstDescendantOfType(ASTVariableExpression.class).getImage();
		ASTIfBlockStatement ifNode = node.getFirstParentOfType(ASTIfBlockStatement.class);
		if(ifNode == null) {
			addViolation(data, node);
		}
		ASTStandardCondition conditionNode = ifNode.getFirstDescendantOfType(ASTStandardCondition.class);
		List<ASTMethodCallExpression> lstMethodCall = conditionNode.findDescendantsOfType(ASTMethodCallExpression.class);
		if(!lstMethodCall.isEmpty()){
			List<String> lstMethodCallName = new ArrayList<>();
			for(ASTMethodCallExpression ele : lstMethodCall) {
				lstMethodCallName.add(ele.getFullMethodName());
			}
			for(Integer i = 0; i <= lstMethodCallName.size(); i++) {
				if(i == lstMethodCallName.size()) {
					addViolation(data, node);
				}else {
					if(lstMethodCallName.get(i).contentEquals(variableName + IS_EMTY) || lstMethodCallName.get(i).contentEquals(variableName + IS_BLANK)) {
						break;
					}
				}
			}
		}else {
			addViolation(data, node);
		}
		return data;
	}
	
	@Override
	public Object visit(ASTDmlUpsertStatement node, Object data) {
		String variableName = node.getFirstDescendantOfType(ASTVariableExpression.class).getImage();
		ASTIfBlockStatement ifNode = node.getFirstParentOfType(ASTIfBlockStatement.class);
		if(ifNode == null) {
			addViolation(data, node);
		}
		ASTStandardCondition conditionNode = ifNode.getFirstDescendantOfType(ASTStandardCondition.class);
		List<ASTMethodCallExpression> lstMethodCall = conditionNode.findDescendantsOfType(ASTMethodCallExpression.class);
		if(!lstMethodCall.isEmpty()){
			List<String> lstMethodCallName = new ArrayList<>();
			for(ASTMethodCallExpression ele : lstMethodCall) {
				lstMethodCallName.add(ele.getFullMethodName());
			}
			for(Integer i = 0; i <= lstMethodCallName.size(); i++) {
				if(i == lstMethodCallName.size()) {
					addViolation(data, node);
				}else {
					if(lstMethodCallName.get(i).contentEquals(variableName + IS_EMTY) || lstMethodCallName.get(i).contentEquals(variableName + IS_BLANK)) {
						break;
					}
				}
			}
		}else {
			addViolation(data, node);
		}
		return data;
	}
}
