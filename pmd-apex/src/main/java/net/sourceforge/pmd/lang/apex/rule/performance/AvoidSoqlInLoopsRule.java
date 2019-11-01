/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.rule.performance;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTDoLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTForEachStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTForLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTSoqlExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTSoslExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ASTWhileLoopStatement;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.AbstractNode;
import net.sourceforge.pmd.lang.ast.Node;

/*
* @created: Lu Chi Hao
*/

public class AvoidSoqlInLoopsRule extends AbstractApexRule {

//    public AvoidSoqlInLoopsRule() {
//        setProperty(CODECLIMATE_CATEGORIES, "Performance");
//        // Note: Often more complicated as just moving the SOQL a few lines.
//        // Involves Maps...
//        setProperty(CODECLIMATE_REMEDIATION_MULTIPLIER, 150);
//        setProperty(CODECLIMATE_BLOCK_HIGHLIGHTING, false);
//    }
//
//    @Override
//    public Object visit(ASTSoqlExpression node, Object data) {
//        if (insideLoop(node) && parentNotReturn(node) && parentNotForEach(node)) {
//            addViolation(data, node);
//        }
//        return data;
//    }
//
//    private boolean parentNotReturn(ASTSoqlExpression node) {
//        return !(node.jjtGetParent() instanceof ASTReturnStatement);
//    }
//
//    private boolean parentNotForEach(ASTSoqlExpression node) {
//        return !(node.jjtGetParent() instanceof ASTForEachStatement);
//    }
//
//    private boolean insideLoop(ASTSoqlExpression node) {
//        Node n = node.jjtGetParent();
//
//        while (n != null) {
//            if (n instanceof ASTDoLoopStatement || n instanceof ASTWhileLoopStatement
//                    || n instanceof ASTForLoopStatement || n instanceof ASTForEachStatement) {
//                return true;
//            }
//            n = n.jjtGetParent();
//        }
//
//        return false;
//    }
	
	@Override
    public Object visit(ASTForLoopStatement node, Object data) {
		//Lap list chua cac soql va sosl trong loop
		List<Node> listNode = checkForSOQL(node);
		
		//Lap list chua tat ca method co soql hoac sosl
    	List<ASTMethod> lstMethod = node.getFirstParentOfType(ASTUserClass.class).findDescendantsOfType(ASTMethod.class);
    	List<String> lstMethodWithSOQL = new ArrayList<>();
    	for(ASTMethod ele : lstMethod) {
    		if(ele.hasDescendantOfType(ASTSoqlExpression.class)) {
    			lstMethodWithSOQL.add(ele.getImage());
    		}
    		if(ele.hasDescendantOfType(ASTSoslExpression.class) && !lstMethodWithSOQL.contains(ele.getImage())) {
    			lstMethodWithSOQL.add(ele.getImage());
    		}
    		if(ele.hasDescendantOfType(ASTMethodCallExpression.class) && !lstMethodWithSOQL.contains(ele.getImage())) {
    			List<ASTMethodCallExpression> lst = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    			for(Integer i = 0; i <= lst.size(); i++) {
    				if(i == lst.size()) {
    					break;
    				}else {
    					if(lst.get(i).getFullMethodName().toLowerCase().contentEquals("database.query") ||
    							lst.get(i).getFullMethodName().toLowerCase().contentEquals("search.query")) {
    						lstMethodWithSOQL.add(ele.getImage());
    						break;
    					}
    				}
    			}
    		}
    	}
    	
    	//Neu trong loop co sosl va soql thi bao loi
    	if(!listNode.isEmpty()) {
    		for(Node ele : listNode) {
    			addViolation(data, ele);
    		}
    	}
    	
    	//Neu khong co method nao chua soql thi ket thuc
    	if(lstMethodWithSOQL.isEmpty()) {
    		return data;
    	}
    	//Lap list chua tat ca method duoc goi trong loop
    	List<ASTMethodCallExpression> lst1 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    	
    	//Neu khong co method nao duoc goi thi khong xet nua
    	if(lst1.isEmpty()) {
    		return data;
    	}
    	for(ASTMethodCallExpression ele : lst1) {
    		
    		//Neu method duoc goi nam trong list chua cac method co soql hoac sosl thi bao loi
    		if(lstMethodWithSOQL.contains(ele.getMethodName())) {
    			addViolation(data, ele);
    			break;
    		
    		//Kiem tra xem method duoc call nay co call toi mot method nao khac chua soql sosl khong
    		}else {
    			if(check(data, ele, lstMethod, lstMethodWithSOQL)) {
    				break;
    				
    			//Di sau them 1 cap nua	
    			}else {
    				for(ASTMethod ele1 : lstMethod) {
    					
    					//Tim vi tri method dang xet duoc khai bao
    					if(ele1.getImage().contentEquals(ele.getFullMethodName())) {
    						
    						//Lap list tat ca cac method duoc goi trong method dang xet
							List<ASTMethodCallExpression> lst2 = ele1.findDescendantsOfType(ASTMethodCallExpression.class);
							for(ASTMethodCallExpression ele2 : lst2) {
								
								//Xem neu method dang xet co trong danh sach cac method co soql sosl khong
								if(lstMethodWithSOQL.contains(ele2.getFullMethodName())) {
									addViolation(data, ele);
									addViolation(data, ele2);
									break;
									
								//Kiem tra xem method duoc call nay co call toi mot method nao khac chua sosl, soql khong
								}else {
									if(check(data, ele2, lstMethod, lstMethodWithSOQL)) {
										addViolation(data, ele);
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
    	List<Node> listNode = checkForSOQL(node);
    	List<ASTMethod> lstMethod = node.getFirstParentOfType(ASTUserClass.class).findDescendantsOfType(ASTMethod.class);
    	List<String> lstMethodWithSOQL = new ArrayList<>();
    	for(ASTMethod ele : lstMethod) {
    		if(ele.hasDescendantOfType(ASTSoqlExpression.class)) {
    			lstMethodWithSOQL.add(ele.getImage());
    		}
    		if(ele.hasDescendantOfType(ASTSoslExpression.class) && !lstMethodWithSOQL.contains(ele.getImage())) {
    			lstMethodWithSOQL.add(ele.getImage());
    		}
    		if(ele.hasDescendantOfType(ASTMethodCallExpression.class) && !lstMethodWithSOQL.contains(ele.getImage())) {
    			List<ASTMethodCallExpression> lst = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    			for(Integer i = 0; i <= lst.size(); i++) {
    				if(i == lst.size()) {
    					break;
    				}else {
    					if(lst.get(i).getFullMethodName().toLowerCase().contentEquals("database.query") ||
    							lst.get(i).getFullMethodName().toLowerCase().contentEquals("search.query")) {
    						lstMethodWithSOQL.add(ele.getImage());
    						break;
    					}
    				}
    			}
    		}
    	}
    	
    	if(!listNode.isEmpty()) {
    		for(Node ele : listNode) {
    			addViolation(data, ele);
    		}
    	}
    	
    	if(lstMethodWithSOQL.isEmpty()) {
    		return data;
    	}
    	List<ASTMethodCallExpression> lst1 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    	if(lst1.isEmpty()) {
    		return data;
    	}
    	for(ASTMethodCallExpression ele : lst1) {
    		if(lstMethodWithSOQL.contains(ele.getMethodName())) {
    			addViolation(data, ele);
    			break;
    		}else {
    			if(check(data, ele, lstMethod, lstMethodWithSOQL)) {
    				break;
    			}else {
    				for(ASTMethod ele1 : lstMethod) {
    					if(ele1.getImage().contentEquals(ele.getFullMethodName())) {
    						
							List<ASTMethodCallExpression> lst2 = ele1.findDescendantsOfType(ASTMethodCallExpression.class);
							for(ASTMethodCallExpression ele2 : lst2) {
								if(lstMethodWithSOQL.contains(ele2.getFullMethodName())) {
									addViolation(data, ele);
									addViolation(data, ele2);
									break;
								}else {
									if(check(data, ele2, lstMethod, lstMethodWithSOQL)) {
										addViolation(data, ele);
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
    	List<Node> listNode = checkForSOQL(node);
    	List<ASTMethod> lstMethod = node.getFirstParentOfType(ASTUserClass.class).findDescendantsOfType(ASTMethod.class);
    	List<String> lstMethodWithSOQL = new ArrayList<>();
    	for(ASTMethod ele : lstMethod) {
    		if(ele.hasDescendantOfType(ASTSoqlExpression.class)) {
    			lstMethodWithSOQL.add(ele.getImage());
    		}
    		if(ele.hasDescendantOfType(ASTSoslExpression.class) && !lstMethodWithSOQL.contains(ele.getImage())) {
    			lstMethodWithSOQL.add(ele.getImage());
    		}
    		if(ele.hasDescendantOfType(ASTMethodCallExpression.class) && !lstMethodWithSOQL.contains(ele.getImage())) {
    			List<ASTMethodCallExpression> lst = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    			for(Integer i = 0; i <= lst.size(); i++) {
    				if(i == lst.size()) {
    					break;
    				}else {
    					if(lst.get(i).getFullMethodName().toLowerCase().contentEquals("database.query") ||
    							lst.get(i).getFullMethodName().toLowerCase().contentEquals("search.query")) {
    						lstMethodWithSOQL.add(ele.getImage());
    						break;
    					}
    				}
    			}
    		}
    	}
    	
    	if(!listNode.isEmpty()) {
    		for(Node ele : listNode) {
    			addViolation(data, ele);
    		}
    	}
    	if(lstMethodWithSOQL.isEmpty()) {
    		return data;
    	}
    	List<ASTMethodCallExpression> lst1 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    	if(lst1.isEmpty()) {
    		return data;
    	}
    	for(ASTMethodCallExpression ele : lst1) {
    		if(lstMethodWithSOQL.contains(ele.getMethodName())) {
    			addViolation(data, ele);
    			break;
    		}else {
    			if(check(data, ele, lstMethod, lstMethodWithSOQL)) {
    				break;
    			}else {
    				for(ASTMethod ele1 : lstMethod) {
    					if(ele1.getImage().contentEquals(ele.getFullMethodName())) {
    						
							List<ASTMethodCallExpression> lst2 = ele1.findDescendantsOfType(ASTMethodCallExpression.class);
							for(ASTMethodCallExpression ele2 : lst2) {
								if(lstMethodWithSOQL.contains(ele2.getFullMethodName())) {
									addViolation(data, ele);
									addViolation(data, ele2);
									break;
								}else {
									if(check(data, ele2, lstMethod, lstMethodWithSOQL)) {
										addViolation(data, ele);
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
    
    public Object visit(ASTDoLoopStatement node, Object data) {
    	List<Node> listNode = checkForSOQL(node);
    	List<ASTMethod> lstMethod = node.getFirstParentOfType(ASTUserClass.class).findDescendantsOfType(ASTMethod.class);
    	List<String> lstMethodWithSOQL = new ArrayList<>();
    	for(ASTMethod ele : lstMethod) {
    		if(ele.hasDescendantOfType(ASTSoqlExpression.class)) {
    			lstMethodWithSOQL.add(ele.getImage());
    		}
    		if(ele.hasDescendantOfType(ASTSoslExpression.class) && !lstMethodWithSOQL.contains(ele.getImage())) {
    			lstMethodWithSOQL.add(ele.getImage());
    		}
    		if(ele.hasDescendantOfType(ASTMethodCallExpression.class) && !lstMethodWithSOQL.contains(ele.getImage())) {
    			List<ASTMethodCallExpression> lst = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    			for(Integer i = 0; i <= lst.size(); i++) {
    				if(i == lst.size()) {
    					break;
    				}else {
    					if(lst.get(i).getFullMethodName().toLowerCase().contentEquals("database.query") ||
    							lst.get(i).getFullMethodName().toLowerCase().contentEquals("search.query") 
    							/* || lst.get(i).getFullMethodName().toLowerCase().contentEquals("search.find")*/
    							) {
    						lstMethodWithSOQL.add(ele.getImage());
    						break;
    					}
    				}
    			}
    		}
    	}
    	
    	if(!listNode.isEmpty()) {
    		for(Node ele : listNode) {
    			addViolation(data, ele);
    		}
    	}
    	if(lstMethodWithSOQL.isEmpty()) {
    		return data;
    	}
    	List<ASTMethodCallExpression> lst1 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    	if(lst1.isEmpty()) {
    		return data;
    	}
    	for(ASTMethodCallExpression ele : lst1) {
    		if(lstMethodWithSOQL.contains(ele.getMethodName())) {
    			addViolation(data, ele);
    			break;
    		}else {
    			if(check(data, ele, lstMethod, lstMethodWithSOQL)) {
    				break;
    			}else {
    				for(ASTMethod ele1 : lstMethod) {
    					if(ele1.getImage().contentEquals(ele.getFullMethodName())) {
    						
							List<ASTMethodCallExpression> lst2 = ele1.findDescendantsOfType(ASTMethodCallExpression.class);
							for(ASTMethodCallExpression ele2 : lst2) {
								if(lstMethodWithSOQL.contains(ele2.getFullMethodName())) {
									addViolation(data, ele);
									addViolation(data, ele2);
									break;
								}else {
									if(check(data, ele2, lstMethod, lstMethodWithSOQL)) {
										addViolation(data, ele);
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
    
    private Boolean check(Object data, ASTMethodCallExpression nodeToCheck, List<ASTMethod> lstMethod, List<String> lstMethodWithDML) {
    	
    	//Xet tung method trong list tat ca method trong class
    	for(ASTMethod ele : lstMethod) {
    		
    		//Neu tim duoc vi tri khai bao method
    		if(ele.getImage().contentEquals(nodeToCheck.getFullMethodName())) {
    			
    			//Lap list tat ca cac method duoc goi trong do
    			List<ASTMethodCallExpression> lst = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    			
    			//Check tung method goi trong list tren
    			for(ASTMethodCallExpression ele1 : lst) {
    				
    				//Neu method co trong list cac method co DML
    				if(lstMethodWithDML.contains(ele1.getFullMethodName())) {
    					
    					//Bao loi ngay method goi no va chinh no
    					addViolation(data, nodeToCheck);
						addViolation(data, ele1);
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    private List<Node> checkForSOQL(AbstractNode node) {
    	//Tao list chua tat ca cac cau SOQL, SOSL
    	List<Node> listNode = new ArrayList<>();
    	if(node.hasDescendantOfType(ASTSoqlExpression.class)) {
    		List<ASTSoqlExpression> list1 = node.findDescendantsOfType(ASTSoqlExpression.class);
    		for(Node ele : list1) {
				listNode.add(ele);
			}
    	}
    	
    	//Tao list chua tat ca cac method database.query va search.query
    	if(node.hasDescendantOfType(ASTMethodCallExpression.class)) {
    		List<ASTMethodCallExpression> list2 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    		for(ASTMethodCallExpression ele : list2) {
    			if(ele.getFullMethodName().toLowerCase().contentEquals("database.query") || 
    				ele.getFullMethodName().toLowerCase().contentEquals("search.query")) {
    				listNode.add(ele);
    			}
			}
    	}
    	
    	if(node.hasDescendantOfType(ASTSoslExpression.class)) {
    		List<ASTSoqlExpression> list3 = node.findDescendantsOfType(ASTSoqlExpression.class);
    		for(Node ele : list3) {
				listNode.add(ele);
			}
    	}
    	return listNode;
    }
}