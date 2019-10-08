package net.sourceforge.pmd.lang.vf.rule;

import java.util.List;

import net.sourceforge.pmd.lang.vf.ast.ASTContent;
import net.sourceforge.pmd.lang.vf.ast.ASTElement;

public class VisualPage19 extends AbstractVfRule{
	@Override
	public Object visit(ASTContent node, Object data) {
		List<ASTElement> lst = node.findDescendantsOfType(ASTElement.class);
		for(ASTElement element : lst) {
			if(element.getName().contentEquals("apex:outputField")) {
				addViolation(data, element);
			}
		}
		return data;
	}
}
