package net.sourceforge.pmd.lang.apex.rule.codestyle;


import net.sourceforge.pmd.lang.apex.ast.ASTField;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTReferenceExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

import java.util.ArrayList;
import java.util.List;

public class General19 extends AbstractApexRule{
	@Override
	public Object visit(ASTField node, Object data) {
		//Bien dem dem so method su dung bien class
		Integer countVar = 0;
		Integer countRef = 0;
		//Neu phat hien bien class thi bat dau xet tu dau class
		ASTUserClass nodeFather = node.getFirstParentOfType(ASTUserClass.class);
		//Lap list chua tat ca method trong class
		List<ASTMethod> lst = nodeFather.findDescendantsOfType(ASTMethod.class);
		Boolean skip = false;
		for(ASTMethod ele : lst) {
			//Bo method clinit khong xet vi do la node ao do PMD tao
			if(!ele.getImage().contentEquals("<clinit>")) {
				//Lap danh sach tat ca cac bien duoc su dung trong method
				List<ASTVariableExpression> lstJr = ele.findDescendantsOfType(ASTVariableExpression.class);
				if(lstJr != null && lstJr.size() > 0) {
					//Lap danh sach chua tat ca cac bien duoc su dung co ten giong bien class dang xet
					List<ASTVariableExpression> lstGrandJr = new ArrayList<>();
					for(ASTVariableExpression eleJr : lstJr) {
						if(eleJr.getImage().contentEquals(node.getImage())) {
							lstGrandJr.add(eleJr);
						}
					}
					//Neu danh sach tao ra phia tren thi tang bien dem lan su dung len 1
					if(lstGrandJr != null && lstGrandJr.size() > 0) {
						countVar = countVar + 1;
						skip = true;
					}
				}
				//Neu bien da duoc su dung duoi dang node o tren thi khong can xet neu duoc su dung o dang node khac nua
				if(skip == false) {
					List<ASTReferenceExpression> lstJr1 = ele.findDescendantsOfType(ASTReferenceExpression.class);
					if(lstJr1 != null && lstJr1.size() > 0) {
						List<ASTReferenceExpression> lstGrandJr1 = new ArrayList<>();
						for(ASTReferenceExpression eleJr : lstJr1) {
							if(eleJr.getImage().contentEquals(node.getImage())) {
								lstGrandJr1.add(eleJr);
							}
						}
						if(lstGrandJr1 != null && lstGrandJr1.size() > 0) {
							countRef = countRef + 1;
						}
					}
				}else {
					skip = false;
				}
			}
		}
		if(countVar == 0 && countRef == 0) {
			addViolationWithMessage(data, node, "[General-10] Nhung bien khai bao khong dung thi delete giup");
		}else if(countVar == 1 && countRef == 1) {
			return data;
		}else if((countVar < 2 && countVar > 0) || (countRef < 2 && countRef > 0)) {
			addViolation(data, node);
		}
		return data;
	}
	
	//Tuong tu o tren nhung xet cac bien cuc bo
	@Override
	public Object visit(ASTVariableDeclaration node, Object data) {
		Integer countVar = 0;
		Integer countRef = 0;
		ASTUserClass nodeFather = node.getFirstParentOfType(ASTUserClass.class);
		List<ASTMethod> lst = nodeFather.findDescendantsOfType(ASTMethod.class);
		for(ASTMethod ele : lst) {
			if(!ele.getImage().contentEquals("<clinit>")) {
				List<ASTVariableExpression> lstJr = ele.findDescendantsOfType(ASTVariableExpression.class);
				if(lstJr != null && lstJr.size() > 0) {
					List<ASTVariableExpression> lstGrandJr = new ArrayList<>();
					for(ASTVariableExpression eleJr : lstJr) {
						if(eleJr.getImage().contentEquals(node.getImage())) {
							lstGrandJr.add(eleJr);
						}
					}
					if(lstGrandJr != null && lstGrandJr.size() > 0) {
						countVar = countVar + 1;
					}
				}
				
				List<ASTReferenceExpression> lstJr1 = ele.findDescendantsOfType(ASTReferenceExpression.class);
				if(lstJr1 != null && lstJr1.size() > 0) {
					List<ASTReferenceExpression> lstGrandJr1 = new ArrayList<>();
					for(ASTReferenceExpression eleJr : lstJr1) {
						if(eleJr.getImage().contentEquals(node.getImage())) {
							lstGrandJr1.add(eleJr);
						}
					}
					if(lstGrandJr1 != null && lstGrandJr1.size() > 0) {
						countRef = countRef + 1;
					}
				}
			}
		}
		if(countVar == 0 && countRef == 0) {
			addViolationWithMessage(data, node, "[General-10] Nhung bien khai bao khong dung thi delete giup");
		}
		return data;
	}
}
	