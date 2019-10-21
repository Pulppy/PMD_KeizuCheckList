package net.sourceforge.pmd.lang.apex.rule.codestyle;



import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTAnnotation;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTModifierNode;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class TestClass07 extends AbstractApexRule{
	@Override
	public Object visit(ASTMethod node, Object data) {
		if(node.getFirstChildOfType(ASTModifierNode.class).isTest()&&!node.getImage().contentEquals("<clinit>")&&!node.getImage().contentEquals("clone")&&!node.getImage().contentEquals("<init>")) {
			ASTUserClass nodeFather = node.getFirstParentOfType(ASTUserClass.class);
			if(nodeFather.hasDescendantOfType(ASTAnnotation.class)) {
				ASTAnnotation annoNode = nodeFather.getFirstDescendantOfType(ASTAnnotation.class);
				if(annoNode.getImage().contentEquals("IsTest")) {
					String name = node.getImage();
					if(name.length() < 4) {
						addViolation(data, node);
					}else{
						String subName = name.substring(0, 4);
						if(!subName.contentEquals("test")) {
							addViolation(data, node);
						}
					}
				}
			}
			if(!node.hasDescendantOfType(ASTMethodCallExpression.class)) {
				addViolationWithMessage(data, node, "Test method phai co Assert");
			}else {
				List<ASTMethodCallExpression> lst = node.findDescendantsOfType(ASTMethodCallExpression.class);
				for(Integer i = 0; i <= lst.size(); i++) {
					if(i == lst.size()) {
						addViolationWithMessage(data, node, "Test method phai co Assert");
						break;
					}
					if(lst.get(i).getFullMethodName().toLowerCase().contentEquals("system.assertequals")) {
						break;
					}
				}
			}
		}
		return data;
	}
}
