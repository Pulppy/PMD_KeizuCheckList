package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTReferenceExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

/*
* @created: Lu Chi Hao
*/

public class General10 extends AbstractApexRule{
	@Override
	public Object visit(ASTVariableDeclaration node, Object data) {
		Integer countVar = 0;
		ASTMethod nodeFather = node.getFirstParentOfType(ASTMethod.class);
			//Khong xet method clinit vi day la mot method do PMD tao
			if(!nodeFather.getImage().contentEquals("<clinit>")) {
				
				//Xet xem torng method bien duoc khai bao co duoc su dung duoi dang ASTVariableExpression, neu co return
				//countVar luon it nhat bang 1 vi luc khai bao bien bien xuat hien 1 lan duoi dang ASTVariableExpression
				List<ASTVariableExpression> lstJr = nodeFather.findDescendantsOfType(ASTVariableExpression.class);
				if(lstJr != null && lstJr.size() > 0) {
					for(ASTVariableExpression eleJr : lstJr) {
						if(eleJr.getImage().contentEquals(node.getImage())) {
							countVar = countVar + 1;
						}
						if(countVar == 2) {
							return data;
						}
					}
				}
				
				//Xet xem torng method bien duoc khai bao co duoc su dung duoi dang ASTReferenceExpression, neu co return
				List<ASTReferenceExpression> lstJr1 = nodeFather.findDescendantsOfType(ASTReferenceExpression.class);
				if(lstJr1 != null && lstJr1.size() > 0) {
					for(ASTReferenceExpression eleJr : lstJr1) {
						if(eleJr.getImage().contentEquals(node.getImage())) {
							return data;
						}
					}
				}
			}
		
		//Neu khong duoc su dung duoi ca 2 dang thi bao loi
		addViolation(data, node);
		return data;
	}
}
