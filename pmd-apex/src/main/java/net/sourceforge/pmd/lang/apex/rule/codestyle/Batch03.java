package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;

public class Batch03 extends AbstractApexRule{
	List<String> CUSTOMSETTINGLIST = new ArrayList<String>() {
		{
			add("getinstance");
			add("getorgdefaults");
			add("getvalues");
		}
	};
//	@Override
//	public Object visit(ASTUserClass node, Object data) {
//		boolean isBatch = false;
//		List<String> interfaceList = node.getInterfaceNames();
//		
//		for(String interfaceName : interfaceList) {
//			if (interfaceName.toLowerCase().contains("batch")) {
//				isBatch = true;
//				break;
//			}
//		}
//		
//		if (!isBatch) {
//			return data;
//		}
//	}
	
	@Override
	public Object visit(ASTMethodCallExpression node, Object data) {
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
	
	private boolean isInStartMethod(ASTMethodCallExpression methodCall) {

		ASTMethod parent = methodCall.getFirstParentOfType(ASTMethod.class);
		if (!parent.getImage().toLowerCase().contentEquals("start")) {
			return false;
		}
		return true;
	}
}
