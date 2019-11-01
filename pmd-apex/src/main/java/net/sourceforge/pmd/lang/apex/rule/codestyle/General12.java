package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTBooleanExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTLiteralExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

/*
* @created: Lu Chi Hao
*/

public class General12 extends AbstractApexRule{
	@Override
	public Object visit(ASTField node, Object data) {
		if(node.getType().length() < 4) {
			return data;
		}
		//Kiem tra bien class dang xet co phai kieu list
		if(node.getType().substring(0,4).contentEquals("List")) {
			ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
			//Lap danh sach cac ham so sanh
			List<ASTBooleanExpression> lst = nodeAncestor.findDescendantsOfType(ASTBooleanExpression.class);
			//Lap danh sach rut gon lai con cac ham so sanh lon hon va bang
			List<ASTBooleanExpression> lstShort = new ArrayList<>();
			List<ASTBooleanExpression> lstShort1 = new ArrayList<>();
			for(ASTBooleanExpression element : lst) {
				if(element.getOperator().toString().contentEquals(">") 
						|| element.getOperator().toString().contentEquals("==")
						|| element.getOperator().toString().contentEquals("!=")) {
					lstShort.add(element);
				}
				
			}
			for(ASTBooleanExpression element : lst) {
				if(element.getOperator().toString().contentEquals(">=") 
						|| element.getOperator().toString().contentEquals("<")) {
					lstShort1.add(element);
				}
				
			}
			String methodName;
			if(!lstShort.isEmpty()) {
				for(ASTBooleanExpression element : lstShort) {
					//Tim trong danh sach da duoc rut gon co phan tu duoc so sanh la phan tu dang xet
					methodName = element.getFirstChildOfType(ASTMethodCallExpression.class).getFullMethodName();
					//Neu su dung .size va so sanh voi 0 thi bao loi
					if(methodName.contentEquals(node.getImage() + ".size")) {
						if(element.getFirstChildOfType(ASTLiteralExpression.class).getImage().contentEquals("0")) {
							addViolation(data, element);
						}
					}
				}
			}
			if(!lstShort1.isEmpty()) {
				for(ASTBooleanExpression element : lstShort1) {
					//Tim trong danh sach da duoc rut gon co phan tu duoc so sanh la phan tu dang xet
					methodName = element.getFirstChildOfType(ASTMethodCallExpression.class).getFullMethodName();
					//Neu su dung .size va so sanh voi 0 thi bao loi
					if(methodName.contentEquals(node.getImage() + ".size")) {
						if(element.getFirstChildOfType(ASTLiteralExpression.class).getImage().contentEquals("1")) {
							addViolation(data, element);
						}
					}
				}
			}
		}
		return data;
	}
	
	//Thuc hien tuong tu ham tren nhung xet nhung bien cuc bo
	@Override
	public Object visit(ASTVariableDeclaration node, Object data) {
		if(node.getType().substring(0,4).contentEquals("List")) {
			ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
			List<ASTBooleanExpression> lst = nodeAncestor.findDescendantsOfType(ASTBooleanExpression.class);
			List<ASTBooleanExpression> lstShort = new ArrayList<>();
			List<ASTBooleanExpression> lstShort1 = new ArrayList<>();
			for(ASTBooleanExpression element : lst) {
				if(element.getOperator().toString().contentEquals(">")
						|| element.getOperator().toString().contentEquals("==")
						|| element.getOperator().toString().contentEquals("!=")){
					lstShort.add(element);
				}
			}
			for(ASTBooleanExpression element : lst) {
				if(element.getOperator().toString().contentEquals(">=") 
						|| element.getOperator().toString().contentEquals("<")) {
					lstShort1.add(element);
				}
				
			}
			String methodName;
			for(ASTBooleanExpression element : lstShort) {
				methodName = element.getFirstChildOfType(ASTMethodCallExpression.class).getFullMethodName();
				if(methodName.contentEquals(node.getImage() + ".size")) {
					if(element.getFirstChildOfType(ASTLiteralExpression.class).getImage().contentEquals("0")) {
						addViolation(data, element);
					}
				}
			}
			for(ASTBooleanExpression element : lstShort1) {
				methodName = element.getFirstChildOfType(ASTMethodCallExpression.class).getFullMethodName();
				if(methodName.contentEquals(node.getImage() + ".size")) {
					if(element.getFirstChildOfType(ASTLiteralExpression.class).getImage().contentEquals("1")) {
						addViolation(data, element);
					}
				}
			}
			
		}
		return data;
	}
}
