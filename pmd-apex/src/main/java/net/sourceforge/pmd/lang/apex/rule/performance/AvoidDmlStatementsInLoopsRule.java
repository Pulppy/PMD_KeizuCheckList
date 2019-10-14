/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.rule.performance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTDmlDeleteStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlInsertStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlMergeStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlUndeleteStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlUpdateStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlUpsertStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDoLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTForEachStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTForLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTLiteralExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTWhileLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ApexNode;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.AbstractNode;
import net.sourceforge.pmd.lang.ast.Node;

public class AvoidDmlStatementsInLoopsRule extends AbstractApexRule {
//
//    public AvoidDmlStatementsInLoopsRule() {
//        setProperty(CODECLIMATE_CATEGORIES, "Performance");
//        // Note: Often more complicated as just moving the SOQL a few lines.
//        // Involves Maps...
//        setProperty(CODECLIMATE_REMEDIATION_MULTIPLIER, 150);
//        setProperty(CODECLIMATE_BLOCK_HIGHLIGHTING, false);
//    }
//
//    @Override
//    public Object visit(ASTDmlDeleteStatement node, Object data) {
//        if (insideLoop(node)) {
//            addViolation(data, node);
//        }
//        return data;
//    }
//
//    @Override
//    public Object visit(ASTDmlInsertStatement node, Object data) {
//        if (insideLoop(node)) {
//            addViolation(data, node);
//        }
//        return data;
//    }
//
//    @Override
//    public Object visit(ASTDmlMergeStatement node, Object data) {
//        if (insideLoop(node)) {
//            addViolation(data, node);
//        }
//        return data;
//    }
//
//    @Override
//    public Object visit(ASTDmlUndeleteStatement node, Object data) {
//        if (insideLoop(node)) {
//            addViolation(data, node);
//        }
//        return data;
//    }
//
//    @Override
//    public Object visit(ASTDmlUpdateStatement node, Object data) {
//        if (insideLoop(node)) {
//            addViolation(data, node);
//        }
//        return data;
//    }
//
//    @Override
//    public Object visit(ASTDmlUpsertStatement node, Object data) {
//        if (insideLoop(node)) {
//            addViolation(data, node);
//        }
//        return data;
//    }
//
//    private boolean insideLoop(AbstractNode node) {
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
    	List<Node> listNode = checkForDML(node);
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
    	for(ASTMethod ele : lst1) {
    		mapMethod.put(ele.getImage(), (ele.hasDescendantOfType(ASTDmlInsertStatement.class) 
    										|| ele.hasDescendantOfType(ASTDmlUpdateStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlDeleteStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlUndeleteStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlUpsertStatement.class)));
    	}
    	for(ASTMethodCallExpression ele : lst2) {
    		if(mapMethod.get(ele.getMethodName())) {
    			addViolation(data, ele);
    		}
    	}
    	return data;
    }
    
    //KSVC Hao PMD-checkList
    //Method with DML inside loop
    @Override
    public Object visit(ASTWhileLoopStatement node, Object data) {
    	List<Node> listNode = checkForDML(node);
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
    	for(ASTMethod ele : lst1) {
    		mapMethod.put(ele.getImage(), (ele.hasDescendantOfType(ASTDmlInsertStatement.class) 
    										|| ele.hasDescendantOfType(ASTDmlUpdateStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlDeleteStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlUndeleteStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlUpsertStatement.class)));
    	}
    	for(ASTMethodCallExpression ele : lst2) {
    		if(mapMethod.get(ele.getMethodName())) {
    			addViolation(data, ele);
    		}
    	}
    	return data;
    }
    
    //KSVC Hao PMD-checkList
    //Method with DML inside loop
    @Override
    public Object visit(ASTForEachStatement node, Object data) {
    	List<Node> listNode = checkForDML(node);
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
    	for(ASTMethod ele : lst1) {
    		mapMethod.put(ele.getImage(), (ele.hasDescendantOfType(ASTDmlInsertStatement.class) 
    										|| ele.hasDescendantOfType(ASTDmlUpdateStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlDeleteStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlUndeleteStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlUpsertStatement.class)));
    	}
    	for(ASTMethodCallExpression ele : lst2) {
    		if(mapMethod.get(ele.getMethodName())) {
    			addViolation(data, ele);
    		}
    	}
    	return data;
    }
    private List<Node> checkForDML(AbstractNode node) {
    	List<Node> listNode = new ArrayList<>();
    	if(node.hasDescendantOfType(ASTDmlInsertStatement.class)) {
    		List<ASTDmlInsertStatement> list1 = node.findDescendantsOfType(ASTDmlInsertStatement.class);
    		for(Node ele : list1) {
				listNode.add(ele);
			}
    	}
    	if(node.hasDescendantOfType(ASTDmlUpdateStatement.class)) {
    		List<ASTDmlUpdateStatement> list2 = node.findDescendantsOfType(ASTDmlUpdateStatement.class);
    		for(Node ele : list2) {
				listNode.add(ele);
			}
    	}
    	if( node.hasDescendantOfType(ASTDmlDeleteStatement.class)) {
    		List<ASTDmlDeleteStatement> list3 = node.findDescendantsOfType(ASTDmlDeleteStatement.class);
    		for(Node ele : list3) {
				listNode.add(ele);
			}
    	}
    	if(node.hasDescendantOfType(ASTDmlUndeleteStatement.class)) {
    		List<ASTDmlUndeleteStatement> list4 = node.findDescendantsOfType(ASTDmlUndeleteStatement.class);
    		for(Node ele : list4) {
				listNode.add(ele);
			}
    	}
    	if(node.hasDescendantOfType(ASTDmlUpsertStatement.class)){
    		List<ASTDmlUpsertStatement> list5 = node.findDescendantsOfType(ASTDmlUpsertStatement.class);
    		for(Node ele : list5) {
				listNode.add(ele);
			}
    	}
    	return listNode;
    }
}
