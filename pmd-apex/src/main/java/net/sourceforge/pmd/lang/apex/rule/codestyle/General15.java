package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTModifierNode;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.Node;

public class General15 extends AbstractApexRule {
	@Override
	public Object visit(ASTUserClass node, Object data) {
		int indexChild = 0;
		ASTModifierNode modifier;
		Node child;
		//Xet tat ca node con cua class
		while(indexChild < node.jjtGetNumChildren()){
			child = node.jjtGetChild(indexChild);
			//Neu node con la mot bien class
			if(child instanceof ASTField) {
				modifier = child.getFirstChildOfType(ASTModifierNode.class);
				//Neu node con khong duoc thiet lap private thi bao loi
				if(!modifier.isPrivate()) {
					addViolation(data, child);
				}
			}
			indexChild++;
		}
		return data;
	}
}
