package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTAnnotation;
import net.sourceforge.pmd.lang.apex.ast.ASTDoLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTForEachStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTForLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ASTWhileLoopStatement;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class General24 extends AbstractApexRule {
	
	@Override
	public Object visit(ASTForLoopStatement node, Object data) {
		List<ASTUserClass> nodeRoot = node.getParentsOfType(ASTUserClass.class);
		List<ASTMethod> lstMethod = nodeRoot.get(0).findDescendantsOfType(ASTMethod.class);
		//Lap list tat ca cac method co @future
		List<String> lstMethodFuture = new ArrayList<>();
		for(ASTMethod elementMethod : lstMethod) {
			if(elementMethod.hasDescendantOfType(ASTAnnotation.class)) {
				ASTAnnotation methodCheck = elementMethod.getFirstDescendantOfType(ASTAnnotation.class);
				if(methodCheck.getImage().contentEquals("Future")) {					
					lstMethodFuture.add(elementMethod.getImage());
				}
			}
		}
		
		//Neu khong co method nao @future thi khong xet nua
		if(lstMethodFuture.isEmpty()) {
			return data;
		}
		
		
		//Xet tat ca cac method duoc goi trong loop
		List<ASTMethodCallExpression> lstToCheck = node.findDescendantsOfType(ASTMethodCallExpression.class);
		for(ASTMethodCallExpression element : lstToCheck) {
			//Kiem tra xem cac method duoc goi co ton trong list cac method @future
			if(lstMethodFuture.contains(element.getFullMethodName())) {
				addViolation(data, element);
				break;
				
			//Kiem tra xem method duoc call nay co call toi mot method nao khac chua @future khong
			}else {
				if(check(data, lstMethod, element, lstMethodFuture)) {
					break;
					
				//Di sau them 1 cap nua	
				}else {
					for(ASTMethod elementMethod : lstMethod) {
						
						//Tim vi tri method dang xet duoc khai bao
						if(elementMethod.getImage().contentEquals(element.getFullMethodName())) {
							
							//Lap list tat ca cac method duoc goi trong method dang xet
							List<ASTMethodCallExpression> lstToCheck2 = elementMethod.findDescendantsOfType(ASTMethodCallExpression.class);
							for(ASTMethodCallExpression element2 : lstToCheck2) {
								
								//Xem neu method dang xet co trong danh sach cac method co @future khong
								if(lstMethodFuture.contains(element2.getFullMethodName())) {
									addViolation(data, element);
									addViolation(data, element2);
									break;
									
								//Kiem tra xem method duoc call nay co call toi mot method nao khac chua @future khong
								}else {
									if(check(data, lstMethod, element2, lstMethodFuture)) {
										addViolation(data, element);
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		return data;
	}
	
	@Override
	public Object visit(ASTForEachStatement node, Object data) {
		List<ASTUserClass> nodeRoot = node.getParentsOfType(ASTUserClass.class);
		List<ASTMethod> lstMethod = nodeRoot.get(0).findDescendantsOfType(ASTMethod.class);
		//Lap list tat ca cac method co @future
		List<String> lstMethodFuture = new ArrayList<>();
		for(ASTMethod elementMethod : lstMethod) {
			if(elementMethod.hasDescendantOfType(ASTAnnotation.class)) {
				ASTAnnotation methodCheck = elementMethod.getFirstDescendantOfType(ASTAnnotation.class);
				if(methodCheck.getImage().contentEquals("Future")) {					
					lstMethodFuture.add(elementMethod.getImage());
				}
			}
		}
		
		//Neu khong co method nao @future thi khong xet nua
		if(lstMethodFuture.isEmpty()) {
			return data;
		}
		
		
		//Xet tat ca cac method duoc goi trong loop
		List<ASTMethodCallExpression> lstToCheck = node.findDescendantsOfType(ASTMethodCallExpression.class);
		for(ASTMethodCallExpression element : lstToCheck) {
			//Kiem tra xem cac method duoc goi co ton trong list cac method @future
			if(lstMethodFuture.contains(element.getFullMethodName())) {
				addViolation(data, element);
				break;
				
			//Kiem tra xem method duoc call nay co call toi mot method nao khac chua @future khong
			}else {
				if(check(data, lstMethod, element, lstMethodFuture)) {
					break;
					
				//Di sau them 1 cap nua	
				}else {
					for(ASTMethod elementMethod : lstMethod) {
						
						//Tim vi tri method dang xet duoc khai bao
						if(elementMethod.getImage().contentEquals(element.getFullMethodName())) {
							
							//Lap list tat ca cac method duoc goi trong method dang xet
							List<ASTMethodCallExpression> lstToCheck2 = elementMethod.findDescendantsOfType(ASTMethodCallExpression.class);
							for(ASTMethodCallExpression element2 : lstToCheck2) {
								
								//Xem neu method dang xet co trong danh sach cac method co @future khong
								if(lstMethodFuture.contains(element2.getFullMethodName())) {
									addViolation(data, element);
									addViolation(data, element2);
									break;
									
								//Kiem tra xem method duoc call nay co call toi mot method nao khac chua @future khong
								}else {
									if(check(data, lstMethod, element2, lstMethodFuture)) {
										addViolation(data, element);
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		return data;
	}
	
	@Override
	public Object visit(ASTWhileLoopStatement node, Object data) {
		List<ASTUserClass> nodeRoot = node.getParentsOfType(ASTUserClass.class);
		List<ASTMethod> lstMethod = nodeRoot.get(0).findDescendantsOfType(ASTMethod.class);
		//Lap list tat ca cac method co @future
		List<String> lstMethodFuture = new ArrayList<>();
		for(ASTMethod elementMethod : lstMethod) {
			if(elementMethod.hasDescendantOfType(ASTAnnotation.class)) {
				ASTAnnotation methodCheck = elementMethod.getFirstDescendantOfType(ASTAnnotation.class);
				if(methodCheck.getImage().contentEquals("Future")) {					
					lstMethodFuture.add(elementMethod.getImage());
				}
			}
		}
		
		//Neu khong co method nao @future thi khong xet nua
		if(lstMethodFuture.isEmpty()) {
			return data;
		}
		
		
		//Xet tat ca cac method duoc goi trong loop
		List<ASTMethodCallExpression> lstToCheck = node.findDescendantsOfType(ASTMethodCallExpression.class);
		for(ASTMethodCallExpression element : lstToCheck) {
			//Kiem tra xem cac method duoc goi co ton trong list cac method @future
			if(lstMethodFuture.contains(element.getFullMethodName())) {
				addViolation(data, element);
				break;
				
			//Kiem tra xem method duoc call nay co call toi mot method nao khac chua @future khong
			}else {
				if(check(data, lstMethod, element, lstMethodFuture)) {
					break;
					
				//Di sau them 1 cap nua	
				}else {
					for(ASTMethod elementMethod : lstMethod) {
						
						//Tim vi tri method dang xet duoc khai bao
						if(elementMethod.getImage().contentEquals(element.getFullMethodName())) {
							
							//Lap list tat ca cac method duoc goi trong method dang xet
							List<ASTMethodCallExpression> lstToCheck2 = elementMethod.findDescendantsOfType(ASTMethodCallExpression.class);
							for(ASTMethodCallExpression element2 : lstToCheck2) {
								
								//Xem neu method dang xet co trong danh sach cac method co @future khong
								if(lstMethodFuture.contains(element2.getFullMethodName())) {
									addViolation(data, element);
									addViolation(data, element2);
									break;
									
								//Kiem tra xem method duoc call nay co call toi mot method nao khac chua @future khong
								}else {
									if(check(data, lstMethod, element2, lstMethodFuture)) {
										addViolation(data, element);
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		return data;
	}
	
	@Override
	public Object visit(ASTDoLoopStatement node, Object data) {
		List<ASTUserClass> nodeRoot = node.getParentsOfType(ASTUserClass.class);
		List<ASTMethod> lstMethod = nodeRoot.get(0).findDescendantsOfType(ASTMethod.class);
		//Lap list tat ca cac method co @future
		List<String> lstMethodFuture = new ArrayList<>();
		for(ASTMethod elementMethod : lstMethod) {
			if(elementMethod.hasDescendantOfType(ASTAnnotation.class)) {
				ASTAnnotation methodCheck = elementMethod.getFirstDescendantOfType(ASTAnnotation.class);
				if(methodCheck.getImage().contentEquals("Future")) {					
					lstMethodFuture.add(elementMethod.getImage());
				}
			}
		}
		
		//Neu khong co method nao @future thi khong xet nua
		if(lstMethodFuture.isEmpty()) {
			return data;
		}
		
		
		//Xet tat ca cac method duoc goi trong loop
		List<ASTMethodCallExpression> lstToCheck = node.findDescendantsOfType(ASTMethodCallExpression.class);
		for(ASTMethodCallExpression element : lstToCheck) {
			//Kiem tra xem cac method duoc goi co ton trong list cac method @future
			if(lstMethodFuture.contains(element.getFullMethodName())) {
				addViolation(data, element);
				break;
				
			//Kiem tra xem method duoc call nay co call toi mot method nao khac chua @future khong
			}else {
				if(check(data, lstMethod, element, lstMethodFuture)) {
					break;
					
				//Di sau them 1 cap nua	
				}else {
					for(ASTMethod elementMethod : lstMethod) {
						
						//Tim vi tri method dang xet duoc khai bao
						if(elementMethod.getImage().contentEquals(element.getFullMethodName())) {
							
							//Lap list tat ca cac method duoc goi trong method dang xet
							List<ASTMethodCallExpression> lstToCheck2 = elementMethod.findDescendantsOfType(ASTMethodCallExpression.class);
							for(ASTMethodCallExpression element2 : lstToCheck2) {
								
								//Xem neu method dang xet co trong danh sach cac method co @future khong
								if(lstMethodFuture.contains(element2.getFullMethodName())) {
									addViolation(data, element);
									addViolation(data, element2);
									break;
									
								//Kiem tra xem method duoc call nay co call toi mot method nao khac chua @future khong
								}else {
									if(check(data, lstMethod, element2, lstMethodFuture)) {
										addViolation(data, element);
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		return data;
	}
	
	//Kiem tra xem co call method @future khong
	private Boolean check(Object data, List<ASTMethod> lstMethod, ASTMethodCallExpression element, List<String> lstMethodFuture) {
		for(ASTMethod elementBig : lstMethod) {
			if(elementBig.getImage().contentEquals(element.getFullMethodName())) {
				List<ASTMethodCallExpression> lstToCheck2 = elementBig.findDescendantsOfType(ASTMethodCallExpression.class);
				for(ASTMethodCallExpression element1 : lstToCheck2) {
					if(lstMethodFuture.contains(element1.getFullMethodName())) {
						addViolation(data, element);
						addViolation(data, element1);
						return true;
					}
				}
				break;
			}
		}
		return false;
	}
}
