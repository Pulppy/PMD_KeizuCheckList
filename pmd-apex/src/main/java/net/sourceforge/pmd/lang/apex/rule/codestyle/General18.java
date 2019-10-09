package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import net.sourceforge.pmd.lang.apex.ast.ASTLiteralExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.Node;
/* @class: MethodCallMstHvCommaAndSpaceBtw
 * 
 * @created: KSVC Truong Trang Ngoc Phuc
 * @date created: 2019/09/30
 * 
 * 
 */
public class General18 extends AbstractApexRule{
	@Override
	public Object visit(ASTMethodCallExpression node, Object data ) {
		List<Node> listNode = new ArrayList<>();
		List<Node> listNode2;
		List<ASTLiteralExpression> listLit = node.findChildrenOfType(ASTLiteralExpression.class);
		List<ASTVariableExpression> listVar = node.findChildrenOfType(ASTVariableExpression.class);
		for(Node ele : listLit) {
			listNode.add(ele);
		}
		for(Node ele : listVar) {
			listNode.add(ele);
		}
		List<Integer> listLine = new ArrayList<Integer>();
		for(Node ele : listNode) {
			if(listLine.isEmpty()) {
				listLine.add(ele.getBeginLine());
			}else {
				for(Integer i = 0; i < listLine.size(); i++) {
					if(listLine.get(i) == ele.getBeginLine()) {
						break;
					}else if(i == listLine.size() - 1) {
						listLine.add(ele.getBeginLine());
					}
				}
			}
		}
		for(Integer line : listLine) {
			listNode2 = new ArrayList<>();
			for(Node ele : listNode) {
				if(ele.getBeginLine() == line) {
					listNode2.add(ele);
				}
			}
			for(Integer i = 0; i < listNode2.size() - 1; i++) {
				for(Integer j = i + 1; j < listNode2.size(); j++) {
					if(listNode2.get(j).getBeginColumn() < listNode2.get(i).getBeginColumn()) {
						 Collections.swap(listNode2, i, j);
					}
				}
			}
			for(Integer i = 0; i < listNode2.size() - 1; i++) {
				if(listNode2.get(i).getEndColumn() + 3 != listNode2.get(i + 1).getBeginColumn()) {
					addViolation(data, listNode2.get(i + 1));
				}
			}
		}
		return data;
	}	
}
