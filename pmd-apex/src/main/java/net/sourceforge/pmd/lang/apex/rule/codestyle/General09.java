package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General09 extends  AbstractApexRule {
	@Override
	public Object visit(ASTField node, Object data ) {
		if(node.getType().contentEquals("Boolean")) {
			if(!node.getImage().substring(0,2).contentEquals("is") && !node.getImage().substring(0,3).contentEquals("has")) {
				addViolation(data, node);
			}
		}
		return data;
	}
	
	@Override
	public Object visit(ASTVariableDeclaration node, Object data ) {
		if(node.getType().contentEquals("Boolean")) {
			if(!node.getImage().substring(0,2).contentEquals("is") && !node.getImage().substring(0,3).contentEquals("has")) {
				addViolation(data, node);
			}
		}
		return data;
	}
}
