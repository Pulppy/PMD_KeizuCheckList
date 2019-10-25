package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTReferenceExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General10 extends AbstractApexRule{
	@Override
	public Object visit(ASTVariableDeclaration node, Object data) {
		Integer countVar = 0;
		Integer countRef = 0;
		ASTMethod nodeFather = node.getFirstParentOfType(ASTMethod.class);
	
			if(!nodeFather.getImage().contentEquals("<clinit>")) {
				List<ASTVariableExpression> lstJr = nodeFather.findDescendantsOfType(ASTVariableExpression.class);
				if(lstJr != null && lstJr.size() > 0) {
					for(ASTVariableExpression eleJr : lstJr) {
						if(eleJr.getImage().contentEquals(node.getImage())) {
							countVar = countVar + 1;
						}
					}
				}
				
				List<ASTReferenceExpression> lstJr1 = nodeFather.findDescendantsOfType(ASTReferenceExpression.class);
				if(lstJr1 != null && lstJr1.size() > 0) {
					for(ASTReferenceExpression eleJr : lstJr1) {
						if(eleJr.getImage().contentEquals(node.getImage())) {
							countRef = countRef + 1;
						}
					}
				}
			}

		if(countVar == 1 && countRef == 0) {

			addViolation(data, node);
		}
		return data;
	}
}
