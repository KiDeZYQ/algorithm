package com.rampage.algorithm.tree;

/**
 * 二叉平衡树中的一种  Adelson-Velsky和E. M. Landis发布的论文《An algorithm for the organization of information》中发表了该树
 * 	二叉查找树可能因为插入的数据按照增序或者降序排列导致树退化成链表
 * 而二叉平衡树有自平衡的能力，二叉平衡树要求任何两个叶子节点的高度差不能超过1，否则就需要进行自平衡旋转
 * @author ziyuqi
 *
 */
public class AVLTree {
	
	private BTreeNode root;
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 左左失衡时右旋，并返回旋转后替换原来节点位置的节点
	 * @param node 待旋转的节点
	 * @return  旋转后替换该节点位置的节点
	 */
	private BTreeNode rightRotate(BTreeNode node) {
		// STEP1: 父子左右节点转换
		BTreeNode leftNode = node.getLeftChild();
		leftNode.setParent(node.getParent());	// 此时无法确定旋转节点是其父节点的左子节点还是右子节点，所以暂时没法设置，需要具体方法中去设置
		node.setLeftChild(leftNode.getRightChild());	leftNode.getRightChild().setParent(node);
		leftNode.setRightChild(node);	node.setParent(leftNode);
		
		// STEP2: 树节点高度转换 转换规则： 当前节点高度为其左右子节点最大高度+1 ，并且先设置左右子节点都发生变化的节点高度
		node.setHeight(Math.max(node.getLeftChild().getHeight(), node.getRightChild().getHeight()) + 1);
		leftNode.setHeight(Math.max(leftNode.getLeftChild().getHeight(), node.getHeight()) + 1);
		return leftNode;
	}
	
	/**
	 * 右右失衡 --- 左旋
	 * @param node  待旋转的节点
	 * @return 旋转后替换原节点位置的节点
	 */
	private BTreeNode leftRotate(BTreeNode node) {
		BTreeNode rightNode = node.getRightChild();
		// STEP1: 父子左右节点转换
		rightNode.setParent(node.getParent());
		node.setRightChild(rightNode.getLeftChild());   rightNode.getLeftChild().setParent(node);
		rightNode.setLeftChild(node);		node.setParent(rightNode);
		
		// STEP2: 高度转换
		node.setHeight(Math.max(node.getLeftChild().getHeight(), node.getRightChild().getHeight()) + 1);
		rightNode.setHeight(Math.max(rightNode.getRightChild().getHeight(), node.getHeight()) + 1);
		return rightNode;
	}
	
	/**
	 * 从底往上看左右失衡 --- 需要先右旋后左旋
	 * @param node 最后待旋转的节点
	 * @return 旋转后替换原node的节点 
	 */ 
	private BTreeNode rightLeftRotate(BTreeNode node) {
		node.setRightChild(rightRotate(node.getRightChild()));
		return leftRotate(node);
	}
	
	/**
	 * 从底往上看右左失衡 --- 需要先左旋后右旋
	 * @param node 最后待旋转的节点
	 * @return 旋转后替换原node的节点 
	 */ 
	private BTreeNode leftRightRotate(BTreeNode node) {
		node.setLeftChild(leftRotate(node.getLeftChild()));
		return rightRotate(node);
	}
	
	/**
	 * 插入一个值
	 * @param value  待插入的值
	 * @return 
	 */
	public BTreeNode insert(int value) {
		return insert(this.root, value);
	}
	
	private BTreeNode insert(BTreeNode curNode, int value) {
		if (curNode == null) {
			curNode = new BTreeNode(value);
		}
		if (curNode.getValue() < value) {
			curNode.setRightChild(insert(curNode.getRightChild(), value));   curNode.getRightChild().setParent(curNode);
			
		} else if (curNode.getValue() > value) {
			
		} else {
			throw new RuntimeException("树中已经存在值为：" + value + "的节点！");
		}
		
		return curNode;
	}

	public BTreeNode search(int value) {
		return search(this.root, value);
  	}

	private BTreeNode search(BTreeNode startNode, int value) {
		if (startNode == null) {
			return null;
		}
		
		if (startNode.getValue() > value) {
			return search(startNode.getLeftChild(), value);
		} else if (startNode.getValue() < value) {
			return search(startNode.getRightChild(), value);
		}
		
		return startNode;
	}
}
