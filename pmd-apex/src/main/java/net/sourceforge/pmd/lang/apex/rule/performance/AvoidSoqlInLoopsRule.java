/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.rule.performance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTDmlDeleteStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlInsertStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlUndeleteStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlUpdateStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlUpsertStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDoLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTForEachStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTForLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTReturnStatement;
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
	
	//KSVC Hao PMD-checkList
    //Method with DML inside loop
    @Override
    public Object visit(ASTForLoopStatement node, Object data) {
    	//Xet trong vong lap co ton tai SOQL
    	List<Node> listNode = checkForSOQL(node);
    	//Neu list tao ra o tren khong trong thi bao loi tat ca cac node trong list
    	if(!listNode.isEmpty()) {
    		for(Node ele : listNode) {
    			addViolation(data, ele);
    		}
    	}
    	//lap list 
    	List<ASTMethodCallExpression> lst2 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    	if(lst2.isEmpty()) {
    		return data;
    	}
    	HashMap<String, Boolean> mapMethod = new HashMap<String, Boolean>();
    	ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
    	List<ASTMethod> lst1 = nodeAncestor.findDescendantsOfType(ASTMethod.class);
    	Boolean check;
    	for(ASTMethod ele : lst1) {
    		List<ASTMethodCallExpression> lstTemp = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    		check = false;
    		for(ASTMethodCallExpression ele1 : lstTemp) {
    			if(ele1.getFullMethodName().toLowerCase().contentEquals("database.query")) {
    				check = true;
    				break;
    			}				
			}
    		mapMethod.put(ele.getImage(), (ele.hasDescendantOfType(ASTSoqlExpression.class) || check));
    	}
    	for(ASTMethodCallExpression ele : lst2) {
    		if(!ele.getFullMethodName().toLowerCase().contentEquals("database.query")) {
    			if(mapMethod.get(ele.getMethodName())) {
    				addViolation(data, ele);
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
    	if(!listNode.isEmpty()) {
    		for(Node ele : listNode) {
    			addViolation(data, ele);
    		}
    	}
    	List<ASTMethodCallExpression> lst2 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    	if(lst2.isEmpty()) {
    		return data;
    	}
    	HashMap<String, Boolean> mapMethod = new HashMap<String, Boolean>();
    	ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
    	List<ASTMethod> lst1 = nodeAncestor.findDescendantsOfType(ASTMethod.class);
    	Boolean check;
    	for(ASTMethod ele : lst1) {
    		List<ASTMethodCallExpression> lstTemp = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    		check = false;
    		for(ASTMethodCallExpression ele1 : lstTemp) {
    			if(ele1.getFullMethodName().toLowerCase().contentEquals("database.query")) {
    				check = true;
    				break;
    			}				
			}
    		mapMethod.put(ele.getImage(), (ele.hasDescendantOfType(ASTSoqlExpression.class) || check));
    	}
    	for(ASTMethodCallExpression ele : lst2) {
    		if(!ele.getFullMethodName().toLowerCase().contentEquals("database.query")) {
    			if(mapMethod.get(ele.getMethodName())) {
    				addViolation(data, ele);
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
    	if(!listNode.isEmpty()) {
    		for(Node ele : listNode) {
    			addViolation(data, ele);
    		}
    	}
    	List<ASTMethodCallExpression> lst2 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    	if(lst2.isEmpty()) {
    		return data;
    	}
    	HashMap<String, Boolean> mapMethod = new HashMap<String, Boolean>();
    	ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
    	List<ASTMethod> lst1 = nodeAncestor.findDescendantsOfType(ASTMethod.class);
    	Boolean check;
    	for(ASTMethod ele : lst1) {
    		List<ASTMethodCallExpression> lstTemp = ele.findDescendantsOfType(ASTMethodCallExpression.class);
    		check = false;
    		for(ASTMethodCallExpression ele1 : lstTemp) {
    			if(ele1.getFullMethodName().toLowerCase().contentEquals("database.query")) {
    				check = true;
    				break;
    			}				
			}
    		mapMethod.put(ele.getImage(), (ele.hasDescendantOfType(ASTSoqlExpression.class) || check));
    	}
    	for(ASTMethodCallExpression ele : lst2) {
    		if(!ele.getFullMethodName().toLowerCase().contentEquals("database.query")) {
    			if(mapMethod.get(ele.getMethodName())) {
    				addViolation(data, ele);
    			}
    		}
    	}
    	return data;
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