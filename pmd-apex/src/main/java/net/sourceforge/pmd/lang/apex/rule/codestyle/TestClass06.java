package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.lang.apex.ast.ASTAnnotation;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class TestClass06 extends AbstractApexRule{
	@Override
	public Object visit(ASTUserClass node, Object data) {
		
		//Kiem tra xem co phai la test class khong
		if(node.hasDescendantOfType(ASTAnnotation.class)) {
			ASTAnnotation annoNode = node.getFirstDescendantOfType(ASTAnnotation.class);
			
			//Kiem tra format ten class
			if(annoNode.getImage().contentEquals("IsTest")) {
				String name = node.getImage();
				
				//Neu ten class it hon 4 chu thi khong xet nua, bao loi (test class phai co chu test)
				if(name.length() < 4) {
					addViolation(data, node);
					
				//Neu hon 4 chu thi xet xem bon chu cuoi co phai la Test hay khong
				}else{
					String subName = name.substring(name.length() - 4);
					if(!subName.contentEquals("Test")) {
						addViolation(data, node);
					}
				}
			}
		}
		return data;
	}
}
