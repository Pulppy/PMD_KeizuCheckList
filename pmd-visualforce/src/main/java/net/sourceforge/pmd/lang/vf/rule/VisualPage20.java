package net.sourceforge.pmd.lang.vf.rule;

import java.util.List;

import net.sourceforge.pmd.lang.vf.ast.ASTContent;
import net.sourceforge.pmd.lang.vf.ast.ASTElement;
import net.sourceforge.pmd.lang.vf.ast.ASTIdentifier;

public class VisualPage20 extends AbstractVfRule{
	@Override
	public Object visit(ASTContent node, Object data) {
		List<ASTElement> lst = node.findDescendantsOfType(ASTElement.class);
		for(ASTElement element : lst) {
			if(element.getName().contentEquals("apex:sectionHeader")) {
				List<ASTIdentifier> lst1 = element.findDescendantsOfType(ASTIdentifier.class);
				for(ASTIdentifier element1 : lst1) {
					if(element1.getImage().contentEquals("HTMLENCODE")) {
						return data;
					}
				}		
				addViolation(data, element);
			}
		}
		return data;
	}
}
