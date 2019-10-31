package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTUserTrigger;
/*
 * General06
 * @created 		: 2019/10/31 Truong Trang Ngoc Phuc
 * @discription		: Trường hợp là class sử dụng trong trigger thì đặt tên theo kiểu : tên Object (xoa chu __c)　＋　“Trigger”
 * @modified		:
 */
public class General06 extends AbstractNamingConventionsRule {
	private static final String TRIGGER = "Trigger";
	@Override
	String displayName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object visit(ASTUserTrigger node, Object data) {
		String triggObjName = node.getTargetName();
		String className = node.getImage();
		String postFixObjName = triggObjName.substring(triggObjName.length() - 3, triggObjName.length());
		
		if (postFixObjName != "__c") {
			triggObjName = triggObjName.replace("__c", "");
		}
		
		if (!(triggObjName + TRIGGER).contentEquals(className)) {
			addViolation(data, node, triggObjName + TRIGGER);
		}
		
		return data;
	}
}
