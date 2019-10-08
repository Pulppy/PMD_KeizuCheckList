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

public class General12 extends AbstractApexRule{
	@Override
	public Object visit(ASTField node, Object data) {
		if(node.getType().substring(0,4).contentEquals("List")) {
			ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
			List<ASTBooleanExpression> lst = nodeAncestor.findDescendantsOfType(ASTBooleanExpression.class);
			List<ASTBooleanExpression> lstShort = new ArrayList<>();
			for(ASTBooleanExpression element : lst) {
				if(element.getOperator().toString().contentEquals(">") || element.getOperator().toString().contentEquals("==")) {
					lstShort.add(element);
				}
			}
			String methodName;
			if(!lstShort.isEmpty()) {
				for(ASTBooleanExpression element : lstShort) {
					methodName = element.getFirstChildOfType(ASTMethodCallExpression.class).getFullMethodName();
					if(methodName.contentEquals(node.getImage() + ".size")) {
						if(element.getFirstChildOfType(ASTLiteralExpression.class).getImage().contentEquals("0")) {
							addViolation(data, element);
						}
					}
				}
			}
		}
		return data;
	}
	
	@Override
	public Object visit(ASTVariableDeclaration node, Object data) {
		if(node.getType().substring(0,4).contentEquals("List")) {
			ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
			List<ASTBooleanExpression> lst = nodeAncestor.findDescendantsOfType(ASTBooleanExpression.class);
			List<ASTBooleanExpression> lstShort = new ArrayList<>();
			for(ASTBooleanExpression element : lst) {
				if(element.getOperator().toString().contentEquals(">") || element.getOperator().toString().contentEquals("==")) {
					lstShort.add(element);
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
		}
		return data;
	}
}
