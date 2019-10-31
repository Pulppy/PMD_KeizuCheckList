package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
/*
 * Batch03
 * @created 		: 2019/10/24 Truong Trang Ngoc Phuc
 * @discription		: Uu tien lay cac doi tuong cutom trong method start
 * @modified		:
 */
public class Batch03 extends AbstractApexRule{
	// Cac cach lay custom setting
	List<String> CUSTOMSETTINGLIST = new ArrayList<String>() {
		{
			add("getinstance");
			add("getorgdefaults");
			add("getvalues");
		}
	};
	
	@Override
	public Object visit(ASTMethodCallExpression node, Object data) {
		ASTUserClass userClass = node.getFirstParentOfType(ASTUserClass.class);
		boolean isBatch = false;
		List<String> interfaceList = userClass.getInterfaceNames();
		// Kiem tra co phai batch hay ko
		if (interfaceList.isEmpty()) {
			return data;
		}
		
		for(String interfaceName : interfaceList) {
			if (interfaceName.toLowerCase().contains("batch")) {
				isBatch = true;
				break;
			}
		}
		
		if (!isBatch) {
			return data;
		}
		// Thuc thi chuong trinh
		for (String customset : CUSTOMSETTINGLIST) {
			if (node.getMethodName().toLowerCase().contentEquals(customset)) {
				if (!isInStartMethod(node)) {
					addViolation(data, node);
					break;
				}
			}
		}
		return data;
	}
	// Kiem tra xem co lay custom setting trong method start hay khong
	private boolean isInStartMethod(ASTMethodCallExpression methodCall) {
		ASTMethod parent = methodCall.getFirstParentOfType(ASTMethod.class);
		if (!parent.getImage().toLowerCase().contentEquals("start")) {
			return false;
		}
		return true;
	}
}
