package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTDmlInsertStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlUpdateStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
/*
 * @class		: VisualPage07
 * @created		: 2019/10/25 Truong Trang Ngoc Phuc
 * @discription	: Trong su ly thuc hien insert, upate DB thi phai su dung savepoint de tao transaction. Truong hop tai lieu ko mo ta thi confirm lai giup.
 * @modified	:
 */
public class VisualPage07 extends AbstractApexRule {
	@Override
	public Object visit(ASTMethod node, Object data) {
		if(node.getFirstParentOfType(ASTUserClass.class).getModifiers().isTest()) {
			return data;
		}
		List<ASTDmlInsertStatement> insertStmList = node.findDescendantsOfType(ASTDmlInsertStatement.class);
		List<ASTDmlUpdateStatement> updateStmList = node.findDescendantsOfType(ASTDmlUpdateStatement.class);
		// Neu ko co DML trong method thi ket thuc
		if (insertStmList.isEmpty() && updateStmList.isEmpty()) {
			return data;
		}
		
		// Kiem tra xem co Database.setSavePoint() va Database.rollback hay ko
		Integer savepointBeginLine = -1;
		Integer rollbackBeginLine = -1;
		boolean hasSetSavePointAndRollback = true;
		List<ASTMethodCallExpression> methodCallList = node.findDescendantsOfType(ASTMethodCallExpression.class);
		if (methodCallList.isEmpty()) {
			addViolation(data, node);
		} else {
			for (ASTMethodCallExpression mce :methodCallList) {
				String fullMethodName = mce.getFullMethodName();
				if (fullMethodName.toLowerCase().contentEquals("database.setsavepoint")) {
					savepointBeginLine = mce.getBeginLine();
				}
				if (fullMethodName.toLowerCase().contentEquals("database.rollback")) {
					rollbackBeginLine = mce.getBeginLine();
				}
			}
		}
		
		// Neu ko co setSavePoint hoac ko co rollback thi vang loi va ket thuc
		if (savepointBeginLine == -1 || rollbackBeginLine == -1) {
			hasSetSavePointAndRollback = false;
		}
		
		// Kiem tra insert
		if (!insertStmList.isEmpty()) {
			for (ASTDmlInsertStatement insertStm : insertStmList) {
				if ( !(insertStm.getBeginLine() >= savepointBeginLine
						&& insertStm.getBeginLine() <= rollbackBeginLine)
						|| !hasSetSavePointAndRollback) {
					addViolation(data, insertStm);
				}
			}
		}
		
		// Kiem tra update
		if (!updateStmList.isEmpty()) {
			for (ASTDmlUpdateStatement updateStm : updateStmList) {
				if ( !(updateStm.getBeginLine() >= savepointBeginLine 
						&& updateStm.getBeginLine() <= rollbackBeginLine)
						|| !hasSetSavePointAndRollback) {
					addViolation(data, updateStm);
				}
			}
		}
		return data;
	}
}
