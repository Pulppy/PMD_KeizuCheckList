package net.sourceforge.pmd.lang.apex.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import net.sourceforge.pmd.lang.apex.ast.ASTBlockStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTStatement;
import net.sourceforge.pmd.lang.apex.ast.ApexNode;

public class ApexSystemDebugInUsed extends AbstractApexRule {
	private static final Set<String> SYSTEMDEBUG = new HashSet<>();

	static {
		SYSTEMDEBUG.add("system.debug");
	}

	@Override
	public Object visit(ASTMethod node, Object data) {
		

		return checkForDebugStatements(node, data);
	}
	
	private Object checkForDebugStatements(ApexNode<?> node, Object data) {
        final List<ASTBlockStatement> blockStatements = node.findDescendantsOfType(ASTBlockStatement.class);
        final List<ASTStatement> statements = new ArrayList<>();
        final List<ASTMethodCallExpression> methodCalls = new ArrayList<>();
        for (ASTBlockStatement blockStatement : blockStatements) {
            statements.addAll(blockStatement.findDescendantsOfType(ASTStatement.class));
            methodCalls.addAll(blockStatement.findDescendantsOfType(ASTMethodCallExpression.class));
        }
        boolean isAssertFound = false;

        for (final ASTMethodCallExpression methodCallExpression : methodCalls) {
            if (SYSTEMDEBUG.contains(methodCallExpression.getFullMethodName().toLowerCase(Locale.ROOT))) {
                isAssertFound = true;
                break;
            }
        }

        if (!isAssertFound) {
            addViolation(data, node);
        }

        return data;
    }
}
