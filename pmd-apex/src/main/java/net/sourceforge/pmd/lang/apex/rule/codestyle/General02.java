package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTBlockStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTTryCatchFinallyBlockStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
/*
 * General02
 * @created 		: 2019/10/31 Truong Trang Ngoc Phuc
 * @discription		: Trong xu ly, những method hoặc funtion public phai co try catch de xu ly loi.
 * @modified		:
 * 
 * */
public class General02 extends AbstractNamingConventionsRule{

	@Override
	String displayName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object visit(ASTMethod node, Object data) {
		// Xet trong constructor
		ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
		if(node.getImage().contentEquals(nodeAncestor.getImage())) {
			return data;
		}
		
		if(node.getImage().length() > 7) {
			if(node.getImage().substring(0, 7).contentEquals("__sfdc_")) {
				return data;
			}
		}
		
		//Neu ham public check xem ca ham co duoc try catch boc khong
		if(node.getModifiers().isPublic()) {
			if (node.findChildrenOfType(ASTBlockStatement.class).isEmpty()) {
				return data;
			}
			ASTBlockStatement blockStatement = node.findChildrenOfType(ASTBlockStatement.class).get(0);
			List<ASTTryCatchFinallyBlockStatement> tryCatchBlockList = blockStatement.findDescendantsOfType(ASTTryCatchFinallyBlockStatement.class);
			if(tryCatchBlockList.isEmpty()) {
				addViolation(data, node);	
			}
		}
		return data;
    }
}
