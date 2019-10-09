package net.sourceforge.pmd.lang.apex.rule.codestyle;


import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTReferenceExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

import java.util.ArrayList;
import java.util.List;

public class General19 extends AbstractApexRule{
	@Override
	public Object visit(ASTField node, Object data) {
		Integer count = 0;
		Integer count1 = 0;
		ASTUserClass nodeFather = node.getFirstParentOfType(ASTUserClass.class);
		List<ASTMethod> lst = nodeFather.findDescendantsOfType(ASTMethod.class);
		for(ASTMethod ele : lst) {
			if(!ele.getImage().contentEquals("<clinit>")) {
				List<ASTVariableExpression> lstJr = ele.findDescendantsOfType(ASTVariableExpression.class);
				if(lstJr != null && lstJr.size() > 0) {
					List<ASTVariableExpression> lstGrandJr = new ArrayList<>();
					for(ASTVariableExpression eleJr : lstJr) {
						if(eleJr.getImage().contentEquals(node.getImage())) {
							lstGrandJr.add(eleJr);
						}
					}
					if(lstGrandJr != null && lstGrandJr.size() > 0) {
						count = count + 1;
					}
				}
				
				List<ASTReferenceExpression> lstJr1 = ele.findDescendantsOfType(ASTReferenceExpression.class);
				if(lstJr1 != null && lstJr1.size() > 0) {
					List<ASTReferenceExpression> lstGrandJr1 = new ArrayList<>();
					for(ASTReferenceExpression eleJr : lstJr1) {
						if(eleJr.getImage().contentEquals(node.getImage())) {
							lstGrandJr1.add(eleJr);
						}
					}
					if(lstGrandJr1 != null && lstGrandJr1.size() > 0) {
						count1 = count1 + 1;
					}
				}
			}
		}
<<<<<<< Updated upstream
=======
		if(count == 0 && count1 == 0) {
			addViolationWithMessage(data, node, "[General-10] Nhung bien khai bao khong dung thi delete giup");
		}
>>>>>>> Stashed changes
		if(count == 1 && count1 == 1) {
			return data;
		}else if((count < 2 && count > 0) || (count1 < 2 && count1 > 0)) {
			addViolation(data, node);
		}
		return data;
	}
}
	