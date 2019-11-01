package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

/*
* @created: Lu Chi Hao
*/

public class General33 extends AbstractApexRule{
	@Override
	public Object visit(ASTMethodCallExpression node, Object data) {
		
		//Tim tat ca ca system.debug va them loi
		if(node.getFullMethodName().toLowerCase().contentEquals("system.debug")) {
			addViolation(data, node);
		}
		return data;
	}
}
