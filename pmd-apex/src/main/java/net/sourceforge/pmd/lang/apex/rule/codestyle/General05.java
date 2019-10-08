package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;

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
		
		if (!interfaceNameList.isEmpty()) {
			for (String intf : interfaceNameList) {
				if (intf.contains("Batch")) {
					isBatch = true;
					break;
				}
			}
		}
		
		if (isBatch) {
			if (!className.substring(classNameLen - 5, classNameLen).contentEquals("Batch")) {
				addViolation(data, node, className);
			}
		}
		
		return data;
	}
}
