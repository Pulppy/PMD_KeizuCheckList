package net.sourceforge.pmd.lang.vf.rule;

import java.util.List;

import net.sourceforge.pmd.lang.vf.ast.ASTAttribute;
import net.sourceforge.pmd.lang.vf.ast.ASTContent;
import net.sourceforge.pmd.lang.vf.ast.ASTElement;
import net.sourceforge.pmd.lang.vf.ast.ASTText;

public class VisualPage16 extends AbstractVfRule{
	@Override
	public Object visit(ASTContent node, Object data) {
		List<ASTElement> lst = node.findDescendantsOfType(ASTElement.class);
		for(ASTElement element : lst) {
			if(element.getName().contentEquals("apex:page")) {
				List<ASTAttribute> lst1 = element.findDescendantsOfType(ASTAttribute.class);
				for(ASTAttribute element1 : lst1) {
					if(element1.getName().contentEquals("controller")) {
						String controllerName = element1.getFirstDescendantOfType(ASTText.class).getImage();
						if(!controllerName.substring(controllerName.length()-10).contentEquals("Controller")) {
							addViolation(data, element1);
							return data;
						}
					}
				}
			}
		}
		return data;
	}
}
