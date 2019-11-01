package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

/*
* @created: Lu Chi Hao
*/

public class General08 extends  AbstractApexRule{
	@Override
	public Object visit(ASTField node, Object data) {
		String name = node.getImage();
		if(name.length() > 5 ) {
			if(!name.substring(name.length() - 5).contentEquals("_PAGE") && node.getModifiers().isPublic()) {
				addViolation(data, node);
			}
		}else {
			if(node.getModifiers().isPublic()) {
				addViolation(data, node);
			}
		}
		return data;
	}
}
