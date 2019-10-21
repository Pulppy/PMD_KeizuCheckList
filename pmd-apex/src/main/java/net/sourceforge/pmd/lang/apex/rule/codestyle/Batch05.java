package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTDmlInsertStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlUpdateStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTDmlUpsertStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class Batch05 extends AbstractApexRule{
	@Override
	public Object visit(ASTDmlInsertStatement node, Object data) {
		
		ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
		List<String> interfaceNameList = nodeAncestor.getInterfaceNames();
		Boolean isBatch = false;
		for(String interfaceName : interfaceNameList){
			if(interfaceName.contains("Batch")) {
				
				isBatch = true;
				break;
			}
		}
		if(!isBatch) {
			return data;
		}
		ASTMethod nodeMethod = node.getFirstParentOfType(ASTMethod.class);
		if(!nodeMethod.getImage().toLowerCase().contentEquals("finish")) {
			
			ASTMethod nodeMethodFinish;
			List<ASTMethod> listMethod = nodeAncestor.findDescendantsOfType(ASTMethod.class); 
			for(ASTMethod ele : listMethod) {
				if(ele.getImage().toLowerCase().contentEquals("finish")) {
					nodeMethodFinish = ele;
					List<ASTMethodCallExpression> listMethodCall = nodeMethodFinish.findDescendantsOfType(ASTMethodCallExpression.class);
					if(listMethodCall.isEmpty()) {
						addViolation(data, node);
						return data;
					}
					List<String> listMethodCallName = new ArrayList<>();
					for(ASTMethodCallExpression ele1 : listMethodCall) {
						listMethodCallName.add(ele1.getFullMethodName());
					}
					if(!listMethodCallName.contains(nodeMethod.getImage())) {
						addViolation(data, node);
						return data;
					}
					break;
				}
			}
		}
		
		return data;
	}
	
	@Override
	public Object visit(ASTDmlUpsertStatement node, Object data) {
		
		ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
		List<String> interfaceNameList = nodeAncestor.getInterfaceNames();
		Boolean isBatch = false;
		for(String interfaceName : interfaceNameList){
			if(interfaceName.contains("Batch")) {
				
				isBatch = true;
				break;
			}
		}
		if(!isBatch) {
			return data;
		}
		ASTMethod nodeMethod = node.getFirstParentOfType(ASTMethod.class);
		if(!nodeMethod.getImage().toLowerCase().contentEquals("finish")) {
			
			ASTMethod nodeMethodFinish;
			List<ASTMethod> listMethod = nodeAncestor.findDescendantsOfType(ASTMethod.class); 
			for(ASTMethod ele : listMethod) {
				if(ele.getImage().toLowerCase().contentEquals("finish")) {
					nodeMethodFinish = ele;
					List<ASTMethodCallExpression> listMethodCall = nodeMethodFinish.findDescendantsOfType(ASTMethodCallExpression.class);
					if(listMethodCall.isEmpty()) {
						addViolation(data, node);
						return data;
					}
					List<String> listMethodCallName = new ArrayList<>();
					for(ASTMethodCallExpression ele1 : listMethodCall) {
						listMethodCallName.add(ele1.getFullMethodName());
					}
					if(!listMethodCallName.contains(nodeMethod.getImage())) {
						addViolation(data, node);
						return data;
					}
					break;
				}
			}
		}
		
		return data;
	}
	
	@Override
	public Object visit(ASTDmlUpdateStatement node, Object data) {
		
		ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
		List<String> interfaceNameList = nodeAncestor.getInterfaceNames();
		Boolean isBatch = false;
		for(String interfaceName : interfaceNameList){
			if(interfaceName.contains("Batch")) {
				
				isBatch = true;
				break;
			}
		}
		if(!isBatch) {
			return data;
		}
		ASTMethod nodeMethod = node.getFirstParentOfType(ASTMethod.class);
		if(!nodeMethod.getImage().toLowerCase().contentEquals("finish")) {
			
			ASTMethod nodeMethodFinish;
			List<ASTMethod> listMethod = nodeAncestor.findDescendantsOfType(ASTMethod.class); 
			for(ASTMethod ele : listMethod) {
				if(ele.getImage().toLowerCase().contentEquals("finish")) {
					nodeMethodFinish = ele;
					List<ASTMethodCallExpression> listMethodCall = nodeMethodFinish.findDescendantsOfType(ASTMethodCallExpression.class);
					if(listMethodCall.isEmpty()) {
						addViolation(data, node);
						return data;
					}
					List<String> listMethodCallName = new ArrayList<>();
					for(ASTMethodCallExpression ele1 : listMethodCall) {
						listMethodCallName.add(ele1.getFullMethodName());
					}
					if(!listMethodCallName.contains(nodeMethod.getImage())) {
						addViolation(data, node);
						return data;
					}
					break;
				}
			}
		}
		
		return data;
	}
}
