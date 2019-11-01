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
	public Object visit(ASTUserClass node, Object data) {
		
		List<String> interfaceNameList = node.getInterfaceNames();
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
		List<ASTMethod> lstMethod = node.findDescendantsOfType(ASTMethod.class);
		List<ASTMethod> lstMethodNew = new ArrayList<>();
		List<ASTMethod> lstMethodBatch = new ArrayList<>();
		for(ASTMethod ele : lstMethod) {
			if(!ele.getImage().contentEquals("start") && !ele.getImage().contentEquals("execute") && !ele.getImage().contentEquals("finish")) {
				if(ele.hasDescendantOfType(ASTDmlInsertStatement.class) 
						|| ele.hasDescendantOfType(ASTDmlUpsertStatement.class) 
						|| ele.hasDescendantOfType(ASTDmlUpdateStatement.class)) {
					lstMethodNew.add(ele);
				}
			}else{
				if(!ele.getImage().contentEquals("finish")) {
					lstMethodBatch.add(ele);
				}
			}
		}
		if(!lstMethod.isEmpty()) {
			List<String> listMethodName = new ArrayList<>();
			for(ASTMethod ele : lstMethodNew) {
				listMethodName.add(ele.getImage());
			}
			for(ASTMethod ele : lstMethodBatch) {
				List<ASTMethodCallExpression> lst1 = ele.findDescendantsOfType(ASTMethodCallExpression.class);
				if(!lst1.isEmpty()) {
					for(ASTMethodCallExpression ele1 : lst1) {
						if(listMethodName.contains(ele1.getFullMethodName())){
							addViolation(data, ele1);
						}
					}
				}
			}
		}
		for(ASTMethod ele : lstMethodBatch) {
			if(ele.hasDescendantOfType(ASTDmlInsertStatement.class)) {
				for(ASTDmlInsertStatement ele1 : ele.findDescendantsOfType(ASTDmlInsertStatement.class)) {
					addViolation(data, ele1);
				}
			}
			if(ele.hasDescendantOfType(ASTDmlUpsertStatement.class)) {
				for(ASTDmlUpsertStatement ele1 : ele.findDescendantsOfType(ASTDmlUpsertStatement.class)) {
					addViolation(data, ele1);
				}
			}
			if(ele.hasDescendantOfType(ASTDmlUpdateStatement.class)) {
				for(ASTDmlUpdateStatement ele1 : ele.findDescendantsOfType(ASTDmlUpdateStatement.class)) {
					addViolation(data, ele1);
				}
			}
		}
		return data;
	}
	
//	@Override
//	public Object visit(ASTDmlInsertStatement node, Object data) {
//		
//		ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
//		List<String> interfaceNameList = nodeAncestor.getInterfaceNames();
//		Boolean isBatch = false;
//		for(String interfaceName : interfaceNameList){
//			if(interfaceName.contains("Batch")) {
//				
//				isBatch = true;
//				break;
//			}
//		}
//		if(!isBatch) {
//			return data;
//		}
//		ASTMethod nodeMethod = node.getFirstParentOfType(ASTMethod.class);
//		if(!nodeMethod.getImage().toLowerCase().contentEquals("finish")) {
//			
//			ASTMethod nodeMethodFinish;
//			List<ASTMethod> listMethod = nodeAncestor.findDescendantsOfType(ASTMethod.class); 
//			for(ASTMethod ele : listMethod) {
//				if(ele.getImage().toLowerCase().contentEquals("finish")) {
//					nodeMethodFinish = ele;
//					List<ASTMethodCallExpression> listMethodCall = nodeMethodFinish.findDescendantsOfType(ASTMethodCallExpression.class);
//					if(listMethodCall.isEmpty()) {
//						addViolation(data, node);
//						return data;
//					}
//					List<String> listMethodCallName = new ArrayList<>();
//					for(ASTMethodCallExpression ele1 : listMethodCall) {
//						listMethodCallName.add(ele1.getFullMethodName());
//					}
//					if(!listMethodCallName.contains(nodeMethod.getImage())) {
//						addViolation(data, node);
//						return data;
//					}
//					break;
//				}
//			}
//		}
//		
//		return data;
//	}
//	
//	@Override
//	public Object visit(ASTDmlUpsertStatement node, Object data) {
//		
//		ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
//		List<String> interfaceNameList = nodeAncestor.getInterfaceNames();
//		Boolean isBatch = false;
//		for(String interfaceName : interfaceNameList){
//			if(interfaceName.contains("Batch")) {
//				
//				isBatch = true;
//				break;
//			}
//		}
//		if(!isBatch) {
//			return data;
//		}
//		ASTMethod nodeMethod = node.getFirstParentOfType(ASTMethod.class);
//		if(!nodeMethod.getImage().toLowerCase().contentEquals("finish")) {
//			
//			ASTMethod nodeMethodFinish;
//			List<ASTMethod> listMethod = nodeAncestor.findDescendantsOfType(ASTMethod.class); 
//			for(ASTMethod ele : listMethod) {
//				if(ele.getImage().toLowerCase().contentEquals("finish")) {
//					nodeMethodFinish = ele;
//					List<ASTMethodCallExpression> listMethodCall = nodeMethodFinish.findDescendantsOfType(ASTMethodCallExpression.class);
//					if(listMethodCall.isEmpty()) {
//						addViolation(data, node);
//						return data;
//					}
//					List<String> listMethodCallName = new ArrayList<>();
//					for(ASTMethodCallExpression ele1 : listMethodCall) {
//						listMethodCallName.add(ele1.getFullMethodName());
//					}
//					if(!listMethodCallName.contains(nodeMethod.getImage())) {
//						addViolation(data, node);
//						return data;
//					}
//					break;
//				}
//			}
//		}
//		
//		return data;
//	}
//	
//	@Override
//	public Object visit(ASTDmlUpdateStatement node, Object data) {
//		
//		ASTUserClass nodeAncestor = node.getFirstParentOfType(ASTUserClass.class);
//		List<String> interfaceNameList = nodeAncestor.getInterfaceNames();
//		Boolean isBatch = false;
//		for(String interfaceName : interfaceNameList){
//			if(interfaceName.contains("Batch")) {
//				
//				isBatch = true;
//				break;
//			}
//		}
//		if(!isBatch) {
//			return data;
//		}
//		ASTMethod nodeMethod = node.getFirstParentOfType(ASTMethod.class);
//		if(!nodeMethod.getImage().toLowerCase().contentEquals("finish")) {
//			
//			ASTMethod nodeMethodFinish;
//			List<ASTMethod> listMethod = nodeAncestor.findDescendantsOfType(ASTMethod.class); 
//			for(ASTMethod ele : listMethod) {
//				if(ele.getImage().toLowerCase().contentEquals("finish")) {
//					nodeMethodFinish = ele;
//					List<ASTMethodCallExpression> listMethodCall = nodeMethodFinish.findDescendantsOfType(ASTMethodCallExpression.class);
//					if(listMethodCall.isEmpty()) {
//						addViolation(data, node);
//						return data;
//					}
//					List<String> listMethodCallName = new ArrayList<>();
//					for(ASTMethodCallExpression ele1 : listMethodCall) {
//						listMethodCallName.add(ele1.getFullMethodName());
//					}
//					if(!listMethodCallName.contains(nodeMethod.getImage())) {
//						addViolation(data, node);
//						return data;
//					}
//					break;
//				}
//			}
//		}
//		
//		return data;
//	}
}
