package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTBlockStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTTryCatchFinallyBlockStatement;

public class General02 extends AbstractNamingConventionsRule{

	@Override
	String displayName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object visit(ASTMethod node, Object data) {
		//Neu ham public check xem ca ham co duoc try catch boc khong
		if(node.getModifiers().isPublic()) {

			ASTBlockStatement blockStatement = node.findChildrenOfType(ASTBlockStatement.class).get(0);
			
			List<ASTTryCatchFinallyBlockStatement> tryCatchBlockList = blockStatement.findDescendantsOfType(ASTTryCatchFinallyBlockStatement.class);
			if(tryCatchBlockList.isEmpty()) {
				addViolation(data, node);	
			}
		}
        return data;
    }
}
