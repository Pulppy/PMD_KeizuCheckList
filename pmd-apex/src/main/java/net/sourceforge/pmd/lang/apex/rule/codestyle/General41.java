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
	public final String IS_EMPTY = ".isEmpty";
	public final String IS_BLANK = ".isBlank";
	@Override
	public Object visit(ASTDmlUpdateStatement node, Object data) {
		
		//Lay ten cua bien duoc thuc hien DML
		String variableName = node.getFirstDescendantOfType(ASTVariableExpression.class).getImage();
		
		//Kiem if dau tien bao boc cau DML
		ASTIfBlockStatement ifNode = node.getFirstParentOfType(ASTIfBlockStatement.class);
		
		//Neu khong ton tai if nao boc DML thi bao loi
		if(ifNode == null) {
			addViolation(data, node);
			return data;
		}
		
		//Xet dieu kien cua vong if do co dang la mot method khong
		ASTStandardCondition conditionNode = ifNode.getFirstDescendantOfType(ASTStandardCondition.class);
		List<ASTMethodCallExpression> lstMethodCall = conditionNode.findDescendantsOfType(ASTMethodCallExpression.class);
		
		//Neu co method thi xet tiep
		if(!lstMethodCall.isEmpty()){
			List<String> lstMethodCallName = new ArrayList<>();
			
			//Lay day du ten method duoc goi
			for(ASTMethodCallExpression ele : lstMethodCall) {
				lstMethodCallName.add(ele.getFullMethodName());
			}
			for(Integer i = 0; i <= lstMethodCallName.size(); i++) {
				
				//Neu xet het list ma khong thay method nao thoa dieu kien thi bao loi
				if(i == lstMethodCallName.size()) {
					addViolation(data, node);
				}else {
					
					//Neu method duoc goi la ten bien + isEmty hoac + isBlank thi khong can xet nua
					if(lstMethodCallName.get(i).contentEquals(variableName + IS_EMPTY) || lstMethodCallName.get(i).contentEquals(variableName + IS_BLANK)) {
						break;
					}
				}
			}
			
		//Neu khong co method nao duoc goi trong dieu kien if thi bao loi
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
			return data;
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
					if(lstMethodCallName.get(i).contentEquals(variableName + IS_EMPTY) || lstMethodCallName.get(i).contentEquals(variableName + IS_BLANK)) {
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
			return data;
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
					if(lstMethodCallName.get(i).contentEquals(variableName + IS_EMPTY) || lstMethodCallName.get(i).contentEquals(variableName + IS_BLANK)) {
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
			return data;
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
					if(lstMethodCallName.get(i).contentEquals(variableName + IS_EMPTY) || lstMethodCallName.get(i).contentEquals(variableName + IS_BLANK)) {
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
