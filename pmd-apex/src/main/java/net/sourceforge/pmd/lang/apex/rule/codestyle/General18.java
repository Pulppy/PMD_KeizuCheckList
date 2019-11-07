package net.sourceforge.pmd.lang.apex.rule.codestyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTLiteralExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTParameter;
import net.sourceforge.pmd.lang.apex.ast.ASTReferenceExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.Node;

/*
* @created: Lu Chi Hao
*/

public class General18 extends AbstractApexRule {
	@Override
	public Object visit(ASTMethod node, Object data) {
		// Khong xet method <init> do PMD tao
		if (node.getImage().contentEquals("<init>")) {
			return data;
		}

		// Tao list chua cac node con la param truyen vao ham
		List<ASTParameter> listNode = node.findChildrenOfType(ASTParameter.class);
		if (listNode.isEmpty()) {
			call(node, data);
			return data;
		}
		// Tao list chua cac hang ma param chiem
		List<Integer> listLine = new ArrayList<Integer>();
		for (ASTParameter ele : listNode) {
			if (listLine.isEmpty()) {
				listLine.add(ele.getBeginLine());
			} else if (!listLine.contains(ele.getBeginLine())) {
				listLine.add(ele.getBeginLine());
			}

		}
		// Tao list chua cac node chung hang
		List<ASTParameter> listNode2 = new ArrayList<>();
		Boolean breakLoop = false;
		for (Integer line : listLine) {
			// Dua nhung param chung ham vao listNode2
			for (ASTParameter ele : listNode) {
				if (ele.getBeginLine() == line) {
					listNode2.add(ele);
				}
			}
			// So sanh va bao loi
			for (Integer i = 0; i < listNode2.size() - 1; i++) {
				// Xet neu kieu du lieu la mot DTO
				if (listNode2.get(i + 1).getType().substring(listNode2.get(i + 1).getType().length() - 3)
						.contentEquals("DTO")) {
					String typeName = listNode2.get(i + 1).getType().split("\\.")[1];
					if ((listNode2.get(i).getEndColumn() + 4 + typeName.length()) != listNode2.get(i + 1)
							.getBeginColumn()) {
						addViolation(data, listNode2.get(i + 1));
						breakLoop = true;
						break;
					}
				} else {
					if ((listNode2.get(i).getEndColumn() + 4 + listNode2.get(i + 1).getType().length()) != listNode2
							.get(i + 1).getBeginColumn()) {
						addViolation(data, listNode2.get(i + 1));
						breakLoop = true;
						break;
					}
				}
			}
			listNode2 = new ArrayList<>();
			if (breakLoop) {
				break;
			}
		}
		call(node, data);
		return data;
	}

	// Goi ham xet method duoc call
	private void call(ASTMethod node, Object data) {
		List<ASTMethodCallExpression> listNodeJr = node.findDescendantsOfType(ASTMethodCallExpression.class);
		Boolean end;
		for (ASTMethodCallExpression ele : listNodeJr) {
			end = visitJr2(ele, data);
			if (end) {
				break;
			}
		}
		return;
	}

//	// Xet cac method duoc call
//	private Boolean visitJr(ASTMethodCallExpression node, Object data) {
//		// Tao list chua cac node la param truyen vao ham duoc goi
//		List<Node> listNode = new ArrayList<>();
//
//		// List chua loai node truyen vao ham duoc goi
//		List<ASTLiteralExpression> listLit = node.findChildrenOfType(ASTLiteralExpression.class);
//		List<ASTVariableExpression> listVar = node.findChildrenOfType(ASTVariableExpression.class);
//		List<ASTMethodCallExpression> listMeth = node.findChildrenOfType(ASTMethodCallExpression.class);
//
//		// Neu ham khong co param truyen vao thi return
//		if (listLit.isEmpty() && listVar.isEmpty() && listMeth.isEmpty()) {
//			return false;
//		}
//
//		// Ghep list vao lam mot
//		if (!listLit.isEmpty()) {
//			for (Node ele : listLit) {
//				listNode.add(ele);
//			}
//		}
//		if (!listVar.isEmpty()) {
//			for (Node ele : listVar) {
//				listNode.add(ele);
//			}
//		}
//		if (!listMeth.isEmpty()) {
//			for (Node ele : listMeth) {
//				listNode.add(ele);
//			}
//		}
//
//		// Tao list chua cac dong ma cac param truyen vao chiem
//		List<Integer> listLine = new ArrayList<Integer>();
//		for (Node ele : listNode) {
//			if (listLine.isEmpty()) {
//				listLine.add(ele.getBeginLine());
//			} else if (!listLine.contains(ele.getBeginLine())) {
//				listLine.add(ele.getBeginLine());
//			}
//		}
//		// Tao list chua cac node chung hang
//		List<Node> listNode2 = new ArrayList<>();
//		for (Integer line : listLine) {
//			// Dua nhung param chung ham vao listNode2
//			for (Node ele : listNode) {
//				if (ele.getBeginLine() == line) {
//					listNode2.add(ele);
//				}
//			}
//			// Sap xep cac phan tu trong listNode2 theo thu tu tang dan begin collumn
//			for (Integer i = 0; i < listNode2.size() - 1; i++) {
//				for (Integer j = i + 1; j < listNode2.size(); j++) {
//					if (listNode2.get(j).getBeginColumn() < listNode2.get(i).getBeginColumn()) {
//						Collections.swap(listNode2, i, j);
//					}
//				}
//			}
//			// So sanh va bao loi
//			for (Integer i = 0; i < listNode2.size() - 1; i++) {
//				// Neu bien truoc la method
//				if (listNode2.get(i) instanceof ASTMethodCallExpression) {
//
//					// Neu bien truoc la method co tham so truyen vao
//					if (listNode2.get(i).hasDescendantOfType(ASTVariableExpression.class)) {
//
//						// Lap list tat ca cac tham so truyen vao
//						List<ASTVariableExpression> lstTemp = listNode2.get(i)
//								.findDescendantsOfType(ASTVariableExpression.class);
//
//						// Neu bien sau truyen vao la method
//						if (listNode2.get(i + 1) instanceof ASTMethodCallExpression) {
//
//							// Neu method sau co prefix
//							if (listNode2.get(i + 1).hasDescendantOfType(ASTReferenceExpression.class)) {
//
//								// Get prefix
//								ASTReferenceExpression nodeTemp = listNode2.get(i + 1)
//										.getFirstDescendantOfType(ASTReferenceExpression.class);
//
//								// Tham so truyen vao cuoi cua method truoc phai cach prefix cua method sau 4
//								// space
//								if (lstTemp.get(lstTemp.size() - 1).getEndColumn() + 4 != nodeTemp.getBeginColumn()) {
//									addViolation(data, listNode2.get(i + 1));
//									return true;
//								}
//
//								// Neu method sau khong co prefix
//							} else {
//
//								// Tham so truyen vao cuoi cua method truoc phai cach method sau 4 space
//								if (lstTemp.get(lstTemp.size() - 1).getEndColumn() + 4 != listNode2.get(i + 1)
//										.getBeginColumn()) {
//									addViolation(data, listNode2.get(i + 1));
//									return true;
//								}
//							}
//
//							// Neu bien sau truyen vao khong phai method
//						} else {
//
//							// Neu bien sau co prefix
//							if (listNode2.get(i + 1).hasDescendantOfType(ASTReferenceExpression.class)) {
//
//								// Get prefix
//								ASTReferenceExpression nodeTemp = listNode2.get(i + 1)
//										.getFirstDescendantOfType(ASTReferenceExpression.class);
//
//								// Tham so cuoi truyen vao phai cach prefix bien sau 4 space
//								if (lstTemp.get(lstTemp.size() - 1).getEndColumn() + 4 != nodeTemp.getBeginColumn()) {
//
//									addViolation(data, listNode2.get(i + 1));
//									return true;
//								}
//
//								// Neu bien sau khong co prefix
//							} else {
//
//								// Tham so cuoi truyen vao phai cach bien sau 4 space
//								if (lstTemp.get(lstTemp.size() - 1).getEndColumn() + 4 != listNode2.get(i + 1)
//										.getBeginColumn()) {
//									addViolation(data, listNode2.get(i + 1));
//									return true;
//								}
//							}
//						}
//
//						// Neu bien truoc la method khong co tham so truyen vao
//					} else {
//
//						// Neu bien sau truyen vao la method
//						if (listNode2.get(i + 1) instanceof ASTMethodCallExpression) {
//
//							// Neu bien sau la method co prefix
//							if (listNode2.get(i + 1).hasDescendantOfType(ASTReferenceExpression.class)) {
//
//								// Get prefix
//								ASTReferenceExpression nodeTemp = listNode2.get(i + 1)
//										.getFirstDescendantOfType(ASTReferenceExpression.class);
//
//								// Method truoc phai cach prefix method sau 5 space
//								if (listNode2.get(i).getEndColumn() + 5 != nodeTemp.getBeginColumn()) {
//									addViolation(data, listNode2.get(i + 1));
//									return true;
//								}
//
//								// Neu bien sau la method khong co prefix
//							} else {
//
//								// Method truoc phai cach method sau 5 space
//								if (listNode2.get(i).getEndColumn() + 5 != listNode2.get(i + 1).getBeginColumn()) {
//									addViolation(data, listNode2.get(i + 1));
//									return true;
//								}
//							}
//
//							// Neu bien sau khong phai method
//						} else {
//
//							// Neu bien sau co prefix
//							if (listNode2.get(i + 1).hasDescendantOfType(ASTReferenceExpression.class)) {
//
//								// Get prefix
//								ASTReferenceExpression nodeTemp = listNode2.get(i + 1)
//										.getFirstDescendantOfType(ASTReferenceExpression.class);
//
//								// Method phai cach prefix bien sau 5 space
//								if (listNode2.get(i).getEndColumn() + 5 != nodeTemp.getBeginColumn()) {
//									addViolation(data, listNode2.get(i + 1));
//									return true;
//								}
//
//								// Neu node sau khong co prefix
//							} else {
//
//								// Method phai cach bien sau 5 space
//								if (listNode2.get(i).getEndColumn() + 5 != listNode2.get(i + 1).getBeginColumn()) {
//									addViolation(data, listNode2.get(i + 1));
//									return true;
//								}
//							}
//						}
//					}
//
//					// Neu bien truoc khong phai method
//				} else {
//
//					// Neu bien sau la method
//					if (listNode2.get(i + 1) instanceof ASTMethodCallExpression) {
//
//						// Neu method sau co prefix
//						if (listNode2.get(i + 1).hasDescendantOfType(ASTReferenceExpression.class)) {
//
//							// Get prefix
//							ASTReferenceExpression nodeTemp = listNode2.get(i + 1)
//									.getFirstDescendantOfType(ASTReferenceExpression.class);
//
//							// Bien truoc phai cach prefix method sau 3 sapce
//							if (listNode2.get(i).getEndColumn() + 3 != nodeTemp.getBeginColumn()) {
//
//								addViolation(data, listNode2.get(i + 1));
//								return true;
//							}
//
//							// Neu bien sau la method khong co prefix
//						} else {
//
//							// Bien truoc phai cach method sau 3 sapce
//							if (listNode2.get(i).getEndColumn() + 3 != listNode2.get(i + 1).getBeginColumn()) {
//								addViolation(data, listNode2.get(i + 1));
//								return true;
//							}
//						}
//
//						// Neu bien sau khong phai method
//					} else {
//
//						// Neu bien sau co prefix
//						if (listNode2.get(i + 1).hasDescendantOfType(ASTReferenceExpression.class)) {
//
//							// Get prefix
//							ASTReferenceExpression nodeTemp = listNode2.get(i + 1)
//									.getFirstDescendantOfType(ASTReferenceExpression.class);
//
//							// Bien truoc phai cach prefix bien sau 3 sapce
//							if (listNode2.get(i).getEndColumn() + 3 != nodeTemp.getBeginColumn()) {
//								addViolation(data, listNode2.get(i + 1));
//								return true;
//							}
//
//							// Neu bien sau khong co prefix
//						} else {
//
//							// Bien truoc phai cach bien sau 3 space
//							if (listNode2.get(i).getEndColumn() + 3 != listNode2.get(i + 1).getBeginColumn()) {
//								addViolation(data, listNode2.get(i + 1));
//								return true;
//							}
//						}
//					}
//				}
//
//			}
//			listNode2 = new ArrayList<>();
//		}
//		return false;
//	}

	private boolean visitJr2(ASTMethodCallExpression node, Object data) {

		// List chua loai node truyen vao ham duoc goi
		List<ASTLiteralExpression> listLit = node.findChildrenOfType(ASTLiteralExpression.class);
		List<ASTVariableExpression> listVar = node.findChildrenOfType(ASTVariableExpression.class);
		List<ASTMethodCallExpression> listMeth = node.findChildrenOfType(ASTMethodCallExpression.class);
		
		// Neu ham khong co param truyen vao thi return
		if (listLit.isEmpty() && listVar.isEmpty() && listMeth.isEmpty()) {
			return false;
		}

		// Ghep list vao lam mot
		List<Node> listNode = addList(listLit, listVar, listMeth);
		// Tao list chua cac dong ma cac param truyen vao chiem
		List<Integer> listLine = new ArrayList<Integer>();
		for (Node ele : listNode) {
			if (listLine.isEmpty()) {
				listLine.add(ele.getBeginLine());
			} else if (!listLine.contains(ele.getBeginLine())) {
				listLine.add(ele.getBeginLine());
			}
		}

		// Tao list chua cac node chung hang
		List<Node> listNode2 = new ArrayList<>();
		for (Integer line : listLine) {
			listNode2 =  new ArrayList<>();
			// Dua nhung param chung ham vao listNode2
			for (Node ele : listNode) {
				if (ele.getBeginLine() == line) {
					listNode2.add(ele);
				}
			}
			
			if(listNode2.size() == 1) {
				continue;
			}
			
			int level;

			listNode2 = organize(listNode2);

			// So sanh va bao loi
			for (Integer i = 0; i < listNode2.size() - 1; i++) {
				level = -1;
				// Neu bien truoc la method
				if (listNode2.get(i) instanceof ASTMethodCallExpression) {
					level++;

					// Tim cac para truyen vao
					List<ASTLiteralExpression> listLitTemp = listNode2.get(i)
							.findChildrenOfType(ASTLiteralExpression.class);
					List<ASTVariableExpression> listVarTemp = listNode2.get(i)
							.findChildrenOfType(ASTVariableExpression.class);
					List<ASTMethodCallExpression> listMethTemp = listNode2.get(i)
							.findChildrenOfType(ASTMethodCallExpression.class);
					if (listLitTemp.isEmpty() && listVarTemp.isEmpty() && listMethTemp.isEmpty()) {
						if (support(listNode2.get(i + 1), listNode2.get(i), data, level)) {
							return true;
						}
					} else {
						List<Node> listNode3 = addList(listLitTemp, listVarTemp, listMethTemp);
						listNode3 = organize(listNode3);

						if (listNode3.get(listNode3.size() - 1) instanceof ASTMethodCallExpression) {
							level++;
							listLitTemp = listNode3.get(listNode3.size() - 1)
									.findChildrenOfType(ASTLiteralExpression.class);
							listVarTemp = listNode3.get(listNode3.size() - 1)
									.findChildrenOfType(ASTVariableExpression.class);
							listMethTemp = listNode3.get(listNode3.size() - 1)
									.findChildrenOfType(ASTMethodCallExpression.class);
							if (listLitTemp.isEmpty() && listVarTemp.isEmpty() && listMethTemp.isEmpty()) {
								if (support(listNode2.get(i + 1), listNode3.get(listNode3.size() - 1), data, level)) {
									return true;
								}
							} else {
								List<Node> listNode4 = addList(listLitTemp, listVarTemp, listMethTemp);
								listNode4 = organize(listNode4);
								if (listNode4.get(listNode4.size() - 1) instanceof ASTMethodCallExpression) {
									level++;
									listLitTemp = listNode4.get(listNode4.size() - 1)
											.findChildrenOfType(ASTLiteralExpression.class);
									listVarTemp = listNode4.get(listNode4.size() - 1)
											.findChildrenOfType(ASTVariableExpression.class);
									listMethTemp = listNode4.get(listNode4.size() - 1)
											.findChildrenOfType(ASTMethodCallExpression.class);
									if (listLitTemp.isEmpty() && listVarTemp.isEmpty() && listMethTemp.isEmpty()) {
										if (support(listNode2.get(i + 1), listNode4.get(listNode4.size() - 1), data,
												level)) {
											return true;
										}
									} else {
										List<Node> listNode5 = addList(listLitTemp, listVarTemp, listMethTemp);
										listNode5 = organize(listNode5);
										if (listNode5.get(listNode5.size() - 1) instanceof ASTMethodCallExpression) {
											continue;
										} else {
											if (support2(listNode2.get(i + 1), listNode5.get(listNode5.size() - 1),
													data, level)) {
												return true;
											}

										}
									}
								} else {

									if (support2(listNode2.get(i + 1), listNode4.get(listNode4.size() - 1), data,
											level)) {
										return true;
									}

								}
							}

						} else {
							if(support2(listNode2.get(i + 1), listNode3.get(listNode3.size() - 1), data, level)) {
								return true;
							}
						}
					}

				} else {
					if(support2(listNode2.get(i + 1), listNode2.get(i), data, level)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private Boolean support(Node aftNode, Node bfrNode, Object data, int level) {
		if (aftNode.hasDescendantOfType(ASTReferenceExpression.class)) {
			// Get prefix
			ASTReferenceExpression nodeTemp = aftNode.getFirstDescendantOfType(ASTReferenceExpression.class);
			if (nodeTemp.getBeginColumn() < aftNode.getEndColumn()) {
				if (bfrNode.getEndColumn() + 3 + level + 2 != nodeTemp.getBeginColumn()) {
					addViolation(data, aftNode);
					return true;
				}
			} else {
				if (bfrNode.getEndColumn() + 3 + level + 2 != aftNode.getBeginColumn()) {
					addViolation(data, aftNode);
					return true;
				}
			}
		} else {
			if (bfrNode.getEndColumn() + 3 + level + 2 != aftNode.getBeginColumn()) {
				addViolation(data, aftNode);
				return true;
			}
		}
		return false;
	}

	private Boolean support2(Node aftNode, Node bfrNode, Object data, int level) {
		if (aftNode.hasDescendantOfType(ASTReferenceExpression.class)) {
			ASTReferenceExpression nodeTemp = aftNode.getFirstDescendantOfType(ASTReferenceExpression.class);
			if (nodeTemp.getBeginColumn() < aftNode.getEndColumn()) {
				if (bfrNode.getEndColumn() + 3 + level + 1 != nodeTemp.getBeginColumn()) {
					addViolation(data, aftNode);
					return true;
				}
			} else {
				if (bfrNode.getEndColumn() + 3 + level + 1 != aftNode.getBeginColumn()) {
					addViolation(data, aftNode);
					return true;
				}
			}

		} else {
			if (bfrNode.getEndColumn() + 3 + level + 1 != aftNode.getBeginColumn()) {
				addViolation(data, aftNode);
				return true;
			}
		}
		return false;
	}
	
	private List<Node> organize(List<Node> lst) {
		for (Integer j = 0; j < lst.size() - 1; j++) {
			for (Integer k = j + 1; k < lst.size(); k++) {
				if (lst.get(k).getBeginColumn() < lst.get(j).getBeginColumn()) {
					Collections.swap(lst, j, k);
				}
			}
		}
		return lst;
	}
	
	private List<Node> addList(List<ASTLiteralExpression> lst1, List<ASTVariableExpression> lst2, List<ASTMethodCallExpression> lst3){
		List<Node> listBig = new ArrayList<>();
		if (!lst1.isEmpty()) {
			for (Node ele : lst1) {
				listBig.add(ele);
			}
		}
		if (!lst2.isEmpty()) {
			for (Node ele : lst2) {
				listBig.add(ele);
			}
		}
		if (!lst3.isEmpty()) {
			for (Node ele : lst3) {
				listBig.add(ele);
			}
		}
		return listBig;
	}
}
