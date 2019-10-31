package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

/*
	* @created: Lu Chi Hao
*/

public class Batch10 extends AbstractApexRule{
	@Override
	public Object visit(ASTMethodCallExpression node, Object data) {
		
		//Xet xem method duoc call co phai la method goi batch chay khong
		if(!node.getFullMethodName().toLowerCase().contentEquals("database.executebatch")) {
			return data;
		}
		
		//Kiem tra xem method hien dang xet co nam trong test class khong
		ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
		if(!nodeAncestor.getModifiers().isTest()) {
			return data;
		}
		
		//Kiem tra xem method chua method goi batch co la mot test method khong
		ASTMethod nodeMethod = node.getFirstParentOfType(ASTMethod.class);
		if(!nodeMethod.getModifiers().isTest()) {
			return data;
		}
		
		//Lap danh sach tat ca cac method duoc goi
		List<ASTMethodCallExpression> listMethodCall = nodeMethod.findDescendantsOfType(ASTMethodCallExpression.class);
		List<String> listMethodCallName = new ArrayList<>();
		for(ASTMethodCallExpression ele : listMethodCall) {
			listMethodCallName.add(ele.getFullMethodName().toLowerCase());
		}
		
		//Kiem tra method test co chua test.startTest và test.stopTest khong
		if(listMethodCallName.contains("test.starttest") && listMethodCallName.contains("test.stoptest")) {
			
			//Kiem tra xem hàm gọi batch có nằm trong start và stop test
			for(ASTMethodCallExpression ele : listMethodCall) {
				if(ele.getFullMethodName().toLowerCase().contentEquals("test.starttest")) {
					if(node.getBeginLine() == ele.getEndLine()) {
						if(node.getEndColumn() < ele.getBeginColumn()) {
							addViolation(data, node);
						}
					}else if(node.getEndLine() < ele.getBeginLine()) {
						addViolation(data, node);
					}
				}else if(ele.getFullMethodName().toLowerCase().contentEquals("test.stoptest")) {
					if(node.getEndLine() == ele.getBeginLine()) {
						if(node.getBeginColumn() > ele.getEndColumn()) {
							addViolation(data, node);
						}
					}else if(node.getBeginLine() > ele.getEndLine()) {
						addViolation(data, node);
					}
					break;
				}
			}
		
		}
		return data;
	}
}
