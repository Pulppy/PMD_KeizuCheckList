package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
/*
 * General05
 * @created 		: 2019/10/31 Truong Trang Ngoc Phuc
 * @discription		: Trường hợp là class sử dụng trong batch thì đặt tên theo kiểu: danh từ ＋ “Batch”
 * @modified		:
 */
public class General05 extends AbstractNamingConventionsRule{

	@Override
	String displayName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object visit(ASTUserClass node, Object data) {
		List<String> interfaceNameList = node.getInterfaceNames();
		String className = node.getImage();
		int classNameLen = className.length();
		boolean isBatch = false;
		// Kiem tra co phai batch hay ko
		if (!interfaceNameList.isEmpty()) {
			for (String intf : interfaceNameList) {
				if (intf.contains("Batch")) {
					isBatch = true;
					break;
				}
			}
		}
		// Kiem tra xem 5 ky tu cuoi cua class co phai "Batch" hay ko
		if (isBatch) {
			if (className.length() < 5) {
				return data;
			}
			if (!className.substring(classNameLen - 5, classNameLen).contentEquals("Batch")) {
				addViolation(data, node, className);
			}
		}
		
		return data;
	}
}
