/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.rule.performance;

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
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ASTWhileLoopStatement;
import net.sourceforge.pmd.lang.apex.ast.ApexNode;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.AbstractNode;
import net.sourceforge.pmd.lang.ast.Node;

public class AvoidDmlStatementsInLoopsRule extends AbstractApexRule {

    public AvoidDmlStatementsInLoopsRule() {
        setProperty(CODECLIMATE_CATEGORIES, "Performance");
        // Note: Often more complicated as just moving the SOQL a few lines.
        // Involves Maps...
        setProperty(CODECLIMATE_REMEDIATION_MULTIPLIER, 150);
        setProperty(CODECLIMATE_BLOCK_HIGHLIGHTING, false);
    }

    @Override
    public Object visit(ASTDmlDeleteStatement node, Object data) {
        if (insideLoop(node)) {
            addViolation(data, node);
        }
        return data;
    }

    @Override
    public Object visit(ASTDmlInsertStatement node, Object data) {
        if (insideLoop(node)) {
            addViolation(data, node);
        }
        return data;
    }

    @Override
    public Object visit(ASTDmlMergeStatement node, Object data) {
        if (insideLoop(node)) {
            addViolation(data, node);
        }
        return data;
    }

    @Override
    public Object visit(ASTDmlUndeleteStatement node, Object data) {
        if (insideLoop(node)) {
            addViolation(data, node);
        }
        return data;
    }

    @Override
    public Object visit(ASTDmlUpdateStatement node, Object data) {
        if (insideLoop(node)) {
            addViolation(data, node);
        }
        return data;
    }

    @Override
    public Object visit(ASTDmlUpsertStatement node, Object data) {
        if (insideLoop(node)) {
            addViolation(data, node);
        }
        return data;
    }

    private boolean insideLoop(AbstractNode node) {
        Node n = node.jjtGetParent();

        while (n != null) {
            if (n instanceof ASTDoLoopStatement || n instanceof ASTWhileLoopStatement
                    || n instanceof ASTForLoopStatement || n instanceof ASTForEachStatement) {
                return true;
            }
            n = n.jjtGetParent();
        }

        return false;
    }
    //KSVC Hao PMD-checkList
    //Method with DML inside loop
    @Override
    public Object visit(ASTForLoopStatement node, Object data) {
    	HashMap<String, Boolean> mapMethod = new HashMap<String, Boolean>();
    	ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
    	List<ASTMethod> lst1 = nodeAncestor.findDescendantsOfType(ASTMethod.class);
    	for(ASTMethod ele : lst1) {
    		mapMethod.put(ele.getImage(), (ele.hasDescendantOfType(ASTDmlInsertStatement.class) 
    										|| ele.hasDescendantOfType(ASTDmlUpdateStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlDeleteStatement.class)));
    	}
    	List<ASTMethodCallExpression> lst2 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    	for(ASTMethodCallExpression ele : lst2) {
    		if(mapMethod.get(ele.getMethodName())) {
    			addViolation(data, node);
    		}
    	}
    	return data;
    }
    
    //KSVC Hao PMD-checkList
    //Method with DML inside loop
    @Override
    public Object visit(ASTWhileLoopStatement node, Object data) {
    	HashMap<String, Boolean> mapMethod = new HashMap<String, Boolean>();
    	ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
    	List<ASTMethod> lst1 = nodeAncestor.findDescendantsOfType(ASTMethod.class);
    	for(ASTMethod ele : lst1) {
    		mapMethod.put(ele.getImage(), (ele.hasDescendantOfType(ASTDmlInsertStatement.class) 
    										|| ele.hasDescendantOfType(ASTDmlUpdateStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlDeleteStatement.class)));
    	}
    	List<ASTMethodCallExpression> lst2 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    	for(ASTMethodCallExpression ele : lst2) {
    		if(mapMethod.get(ele.getMethodName())) {
    			addViolation(data, node);
    		}
    	}
    	return data;
    }
    
  //KSVC Hao PMD-checkList
    //Method with DML inside loop
    @Override
    public Object visit(ASTForEachStatement node, Object data) {
    	HashMap<String, Boolean> mapMethod = new HashMap<String, Boolean>();
    	ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
    	List<ASTMethod> lst1 = nodeAncestor.findDescendantsOfType(ASTMethod.class);
    	for(ASTMethod ele : lst1) {
    		mapMethod.put(ele.getImage(), (ele.hasDescendantOfType(ASTDmlInsertStatement.class) 
    										|| ele.hasDescendantOfType(ASTDmlUpdateStatement.class)
    										|| ele.hasDescendantOfType(ASTDmlDeleteStatement.class)));
    	}
    	List<ASTMethodCallExpression> lst2 = node.findDescendantsOfType(ASTMethodCallExpression.class);
    	for(ASTMethodCallExpression ele : lst2) {
    		if(mapMethod.get(ele.getMethodName())) {
    			addViolation(data, node);
    		}
    	}
    	return data;
    }
}
