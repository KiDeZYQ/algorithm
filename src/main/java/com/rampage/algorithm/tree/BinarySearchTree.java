package com.rampage.algorithm.tree;

import java.util.Stack;

/**
 * 二叉查找树： 
 *			二叉查找树与堆的区别： 堆只要保证堆顶的父节点大于子节点即可，二叉查找树则为了查找效率规定: 父节点大于所有左子树节点，小于所有右子树节点
 * 		这里为了支持添加重复元素，规定左侧元素必须小于父节点 右侧可以大于等于
 * @author ziyuqi
 *
 */
public class BinarySearchTree {
	
	/**
	 * 头结点
	 */
	private BTreeNode root;
	
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		
		// 添加测试
		bst.add(1);
		bst.add(1);
		bst.add(1);
		bst.add(-1);
		bst.add(-2);
		bst.add(-3);
		bst.add(3);
		bst.add(4);
		bst.add(1);
		bst.add(2);
		bst.add(5);
		/**
		 * 			 1
		 * 	     -1	1
		 * 	  -2			1
		 * 	-3 				1
		 * 							3
		 * 						2		4
		 *                                  5
		 */
		// bst.preorderTraversal(bst.root);
		// bst.inorderTraversal(bst.root);					// 中序遍历输出的数字其实就是排好序的
		// bst.postorderTraversal(bst.root);
		
		
		// 删除节点
		bst.remove(1);			// 删除头结点1 ： 左右都有子节点
		// bst.preorderTraversal(bst.root);
		bst.remove(-3);		// 删除没有任何子节点的节点
		// bst.preorderTraversal(bst.root);
		bst.remove(4);			// 删除只有一个子节点的节点
		// bst.preorderTraversal(bst.root);
		bst.remove(17);		// 删除一个不存在的节点
		bst.preorderTraversal(bst.root);
		
		// 检测节点是否存在
		System.out.print(bst.exists(1));
		System.out.print(bst.exists(5));
		System.out.print(bst.exists(-2));
		System.out.print(bst.exists(-11));
	}
	
	private boolean exists(int value) {
		BTreeNode curNode = root;
		while (curNode != null) {
			if (curNode.getValue() > value) {
				curNode = curNode.getLeftChild();
			} else if (curNode.getValue() < value) {
				curNode = curNode.getRightChild();
			} else {
				return true;
			}
	 	}
 		
		return false;
	}
	
	public void preorderTraversal(BTreeNode node) {
		if (node == null) {
			return;
		}
		
		// 递归版实现 先输出当前节点，再左子节点，最后右子节点
		/*System.out.println(node);
		preorderTraversal(node.getLeftChild());
		preorderTraversal(node.getRightChild());*/
		
		// 非递归版本实现，利用栈
		Stack<BTreeNode> nodeStack = new Stack<BTreeNode>();
		nodeStack.push(node);
		BTreeNode curNode = null;
		while (!nodeStack.isEmpty()) {
			// 先序遍历先输出当前节点，所以这里立即将当前节点压出栈并输出
			curNode = nodeStack.pop();
			System.out.println(curNode);
			
			// 后面输出先左再右，根据栈的后进先出特点，应该先压右再压左
			if (curNode.getRightChild() != null) {
				nodeStack.push(curNode.getRightChild());
			}
			
			if (curNode.getLeftChild() != null) {
				nodeStack.push(curNode.getLeftChild());
			}
 		}
	}
	
	public void inorderTraversal(BTreeNode node) {
		if (node == null) {
			return;
		}
		
		// 递归版实现 先输出左子节点，然后当前节点，最后右子节点
		/*inorderTraversal(node.getLeftChild());
		System.out.println(node);
		inorderTraversal(node.getRightChild());*/
		
		// 非递归版实现，也是通过Stack实现
		Stack<BTreeNode> nodeStack = new Stack<BTreeNode>();
		BTreeNode curNode = node;
		while (!nodeStack.isEmpty() || curNode != null) {
			if (curNode != null) {
				nodeStack.push(curNode);
				curNode = curNode.getLeftChild();
			} else {
				curNode = nodeStack.pop();
				System.out.println(curNode);
				curNode = curNode.getRightChild();
			}
		}
	}
	
	public void postorderTraversal(BTreeNode node) {
		if (node == null) {
			return;
		}
		
		// 递归版实现 先输出左子节点，然后右子节点，最后当前节点
		/*postorderTraversal(node.getLeftChild());
		postorderTraversal(node.getRightChild());
		System.out.println(node);*/
		
		// 非递归版: 双Stack实现
		Stack<BTreeNode> stack1 = new Stack<BTreeNode>();
		Stack<BTreeNode> stack2 = new Stack<BTreeNode>();
		stack1.push(node);
		BTreeNode curNode = null;
		while (!stack1.isEmpty()) {
			curNode = stack1.pop();
			stack2.push(curNode);
			if (curNode.getLeftChild() != null) {
				stack1.push(curNode.getLeftChild());
			}
			if (curNode.getRightChild() != null) {
				stack1.push(curNode.getRightChild());
			}
		}
		
		while (!stack2.isEmpty()) {
			System.out.println(stack2.pop());
		}
		
	}
	
	
	/**
	 * 移除第一个值为传入值的节点
	 * @param value	待移除节点的值
	 * @return  true, 移除成功; false, 移除失败
	 */
	private boolean remove(int value) {
		/**
		 * 二叉查找树的删除分以下几种情况：
		 * 1. 删除的节点没有子节点      ---- 直接将当前父节点的子节点置为null
		 * 2. 删除的节点有一个子节点  ---- 将字节点的值设置到当前节点，然后删除当前节点对子节点的引用
		 * 3. 删除的节点有两个子节点  ---- 将右子树的最小节点的值与当前节点值替换，然后删除那个左子节点
		 */
		if (root == null) {
			return false;
		}
		BTreeNode curNode = root;
		BTreeNode parentNode = root;
		while (curNode != null) {
			if (curNode.getValue() == value) {
				removeNode(curNode, parentNode);
				return true;
			} else if (curNode.getValue() > value) {
				parentNode = curNode;			// 这里借用parentNode是为了在真正删除的时候不需要在分别根据删除的节点是否是根节点来进行各种判断
				curNode = curNode.getLeftChild();
			} else {
				parentNode = curNode;			// 这里借用parentNode是为了在真正删除的时候不需要在分别根据删除的节点是否是根节点来进行各种判断
				curNode = curNode.getRightChild();
			}
		}
		return false;
	}
	
	private void removeNode(BTreeNode curNode, BTreeNode parentNode) {
		// 如果当前节点没有子节点，直接将父节点应用置为null即可
		if (curNode.getLeftChild() == null && curNode.getRightChild() == null) {
 			if (parentNode.getRightChild() == curNode) {
 				parentNode.setRightChild(null);
			} else {
				parentNode.setLeftChild(null);
			}
			return;
		} 
		
		// 只有一个子节点，则将子节点的值放入当前
		BTreeNode leftChild = curNode.getLeftChild();
		BTreeNode rightChild = curNode.getRightChild();
		if (leftChild != null && rightChild == null) {
			// 左子节点不为空，右子节点为空
			parentNode.setLeftChild(leftChild);
			curNode.setLeftChild(null);
			curNode.setParent(null);
			decrementDepth(leftChild);
		} else if (rightChild != null && leftChild == null) {
			parentNode.setRightChild(rightChild);
			curNode.setRightChild(null);
			curNode.setParent(null);
			decrementDepth(rightChild);
		} else {
			// 两个节点都不为空，则查找右子树最小节点 --- 右子树中第一个不含左子节点的节点
			BTreeNode searchNode = curNode.getRightChild();
			while (searchNode.getLeftChild() != null) {
				searchNode = searchNode.getLeftChild();
			}
			curNode.setValue(searchNode.getValue());
			removeNode(searchNode, searchNode.getParent());
		}
	}

	/**
	 * 添加元素
	 * @param data  元素的值
	 */
	public void add(int value) {
		BTreeNode newNode = new BTreeNode(value);
		// 如果根节点为空，则该节点为根节点
		if (root == null) {
			root = newNode;
			return;
		}
		
		// 找打合适的位置再插入 
		BTreeNode curNode = root;
		BTreeNode parentNode = null;
		while (curNode != null) {
			parentNode = curNode;
			if (value == curNode.getValue()) {
				break;
			}
			if (value < curNode.getValue()) {
				curNode = curNode.getLeftChild();
			} else {
				curNode = curNode.getRightChild();
			}
			newNode.setDepth(newNode.getDepth() + 1);
		}
		
		if (parentNode.getValue() > value) {
			// 插入到左侧空节点
			parentNode.setLeftChild(newNode);
			newNode.setParent(parentNode);
		} else if (parentNode.getValue() < value) {
			// 插入到右侧空节点
			parentNode.setRightChild(newNode);
			newNode.setParent(parentNode);
		} else {
			// 插入到右侧与其值相等的节点前面  --- 让原来的右侧节点下沉
			BTreeNode rightSub = parentNode.getRightChild();
			if (rightSub != null) {
				rightSub.setParent(newNode);
				incrementDepth(rightSub);			// 如果不存储深度的话，这一部分操作可以略去
			}
			parentNode.setRightChild(newNode);
			newNode.setDepth(newNode.getDepth() + 1);
			newNode.setParent(parentNode);
			newNode.setRightChild(rightSub);
		}
	}
	
	
	/**
	 * 增加当前节点和其下所有子节点的深度
	 * @param node   起始节点
	 */
	private void incrementDepth(BTreeNode node) {
		if (node == null) {
			return;
		}
		node.setDepth(node.getDepth() + 1);
		incrementDepth(node.getLeftChild());
		incrementDepth(node.getRightChild());
	}
	
	/**
	 * 减少当前节点和其下所有子节点的深度
	 * @param node   起始节点
	 */
	private void decrementDepth(BTreeNode node) {
		if (node == null) {
			return;
		}
		node.setDepth(node.getDepth() - 1);
		decrementDepth(node.getLeftChild());
		decrementDepth(node.getRightChild());
	}
}
