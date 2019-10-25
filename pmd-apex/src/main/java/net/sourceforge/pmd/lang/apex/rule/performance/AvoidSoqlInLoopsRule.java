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
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ASTWhileLoopStatement;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.AbstractNode;
import net.sourceforge.pmd.lang.ast.Node;

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
		List<Node> listNode = checkForSOQL(node);
    	List<ASTMethod> lstMethod = node.getFirstParentOfType(ASTUserClass.class).findDescendantsOfType(ASTMethod.class);
    	List<String> lstMethodWithSOQL = new ArrayList<>();
    	for(ASTMethod ele : lstMethod) {
    		if(ele.hasDescendantOfType(ASTSoqlExpression.class)) {
    			lstMethodWithSOQL.add(ele.getImage());
    		}
    		if(ele.hasDescendantOfType(ASTMethodCallExpression.class)) {
    			List<ASTMethodCallExpression> lst = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    			for(Integer i = 0; i <= lst.size(); i++) {
    				if(i == lst.size()) {
    					break;
    				}else {
    					if(lst.get(i).getFullMethodName().toLowerCase().contentEquals("database.query")) {
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
    
    //KSVC Hao PMD-checkList
    //Method with DML inside loop
    @Override
    public Object visit(ASTWhileLoopStatement node, Object data) {
    	List<Node> listNode = checkForSOQL(node);
    	List<ASTMethod> lstMethod = node.getFirstParentOfType(ASTUserClass.class).findDescendantsOfType(ASTMethod.class);
    	List<String> lstMethodWithSOQL = new ArrayList<>();
    	for(ASTMethod ele : lstMethod) {
    		if(ele.hasDescendantOfType(ASTSoqlExpression.class)) {
    			lstMethodWithSOQL.add(ele.getImage());
    		}
    		if(ele.hasDescendantOfType(ASTMethodCallExpression.class)) {
    			List<ASTMethodCallExpression> lst = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    			for(Integer i = 0; i <= lst.size(); i++) {
    				if(i == lst.size()) {
    					break;
    				}else {
    					if(lst.get(i).getFullMethodName().toLowerCase().contentEquals("database.query")) {
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
    
    //KSVC Hao PMD-checkList
    //Method with DML inside loop
    @Override
    public Object visit(ASTForEachStatement node, Object data) {
    	List<Node> listNode = checkForSOQL(node);
    	List<ASTMethod> lstMethod = node.getFirstParentOfType(ASTUserClass.class).findDescendantsOfType(ASTMethod.class);
    	List<String> lstMethodWithSOQL = new ArrayList<>();
    	for(ASTMethod ele : lstMethod) {
    		if(ele.hasDescendantOfType(ASTSoqlExpression.class)) {
    			lstMethodWithSOQL.add(ele.getImage());
    		}
    		if(ele.hasDescendantOfType(ASTMethodCallExpression.class)) {
    			List<ASTMethodCallExpression> lst = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    			for(Integer i = 0; i <= lst.size(); i++) {
    				if(i == lst.size()) {
    					break;
    				}else {
    					if(lst.get(i).getFullMethodName().toLowerCase().contentEquals("database.query")) {
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
    		if(ele.hasDescendantOfType(ASTMethodCallExpression.class)) {
    			List<ASTMethodCallExpression> lst = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    			for(Integer i = 0; i <= lst.size(); i++) {
    				if(i == lst.size()) {
    					break;
    				}else {
    					if(lst.get(i).getFullMethodName().toLowerCase().contentEquals("database.query")) {
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
    	//Tao list chua tat ca cac cau SOQL
    	List<Node> listNode = new ArrayList<>();
    	if(node.hasDescendantOfType(ASTSoqlExpression.class)) {
    		List<ASTSoqlExpression> list1 = node.findDescendantsOfType(ASTSoqlExpression.class);
    		for(Node ele : list1) {
				listNode.add(ele);
			}
    	}
    	if(node.hasDescendantOfType(ASTMethodCallExpression.class)) {
    		List<ASTMethodCallExpression> list2 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    		for(ASTMethodCallExpression ele : list2) {
    			if(ele.getFullMethodName().toLowerCase().contentEquals("database.query")) {
    				listNode.add(ele);
    			}				
			}
    	}
    	return listNode;
    }
}