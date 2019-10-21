package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTSoqlExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

import java.util.ArrayList;
import java.util.List;


public class General43 extends AbstractApexRule{
	@Override
	public Object visit(ASTUserClass node, Object data) {
		List<ASTSoqlExpression> lst1 = node.findDescendantsOfType(ASTSoqlExpression.class);
		List<ASTMethodCallExpression> lst2 = node.findDescendantsOfType(ASTMethodCallExpression.class);
		List<ASTMethodCallExpression> lst2Short = new ArrayList<>();
		for(ASTMethodCallExpression ele : lst2) {
			if(ele.getFullMethodName().toLowerCase().contentEquals("database.query")) {
				lst2Short.add(ele);
			}
		}
		if((lst1.size() + lst2Short.size()) > 10) {
			for(ASTSoqlExpression ele : lst1) {
				addViolation(data, ele);
			}
			for(ASTMethodCallExpression ele : lst2Short) {
				addViolation(data, ele);
			}
		}
		return data;
	}
}
