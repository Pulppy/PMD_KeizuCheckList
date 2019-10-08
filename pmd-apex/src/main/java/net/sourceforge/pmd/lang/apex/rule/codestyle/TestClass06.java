package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTAnnotation;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class TestClass06 extends AbstractApexRule{
	@Override
	public Object visit(ASTUserClass node, Object data) {
		if(node.hasDescendantOfType(ASTAnnotation.class)) {
			ASTAnnotation annoNode = node.getFirstDescendantOfType(ASTAnnotation.class);
			if(annoNode.getImage().contentEquals("IsTest")) {
				String name = node.getImage();
				if(name.length() < 4) {
					addViolation(data, node);
				}else{
					String subName = name.substring(name.length() - 4);
					if(!subName.contentEquals("Test")) {
						addViolation(data, node);
					}
				}
			}
		}
		return data;
	}
}
