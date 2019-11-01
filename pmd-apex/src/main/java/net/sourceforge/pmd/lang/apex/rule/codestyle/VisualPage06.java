package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

/*
* @created: Lu Chi Hao
*/

public class VisualPage06 extends AbstractApexRule {
	@Override
	public Object visit(ASTMethodCallExpression node, Object data) {
		
		//Kiem tra xem node dang xet co phai la ham dang can xet khong
		if(node.getFullMethodName().toLowerCase().contentEquals("apexpages.addmessage")) {			
			List<ASTVariableExpression> lst = node.findDescendantsOfType(ASTVariableExpression.class);
			String severity;
			for(ASTVariableExpression element : lst) {
				severity = element.getImage();
				
				//Bao loi neu severity khong thoa dieu kien
				if(severity.contentEquals("INFO") || severity.contentEquals("WARNING") || severity.contentEquals("ERROR") || severity.contentEquals("CONFIRM")) {
					addViolation(data, node);
				}
			}
		}
		return data;
	}
}
