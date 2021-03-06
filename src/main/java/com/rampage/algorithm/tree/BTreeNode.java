package com.rampage.algorithm.tree;

/**
 * 二叉树节点
 * 
 * @author ziyuqi
 *
 */
public class BTreeNode {
	/**
	 * 存储的数据
	 */
	private int value;

	/**
	 * 左子节点
	 */
	private BTreeNode leftChild;

	/**
	 * 右子节点
	 */
	private BTreeNode rightChild;

	/**
	 * 父节点
	 */
	private BTreeNode parent;

	/**
	 * 节点颜色： 红黑树用的到
	 */
	private Color color;
	
	/**
	 * 深度
	 */
	private int depth  = 1;
	
	/**
	 * 树的高度： 在平衡二叉树和红黑树的时候用的到 如果这时候用depth那么发生旋转之后，所有子节点的depth都得变化
	 */
	private int height = 1;
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public BTreeNode(int value) {
		this.value = value;
	}

	public static enum Color {
		BLACK, RED
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public BTreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public BTreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(BTreeNode rightChild) {
		this.rightChild = rightChild;
	}

	public BTreeNode getParent() {
		return parent;
	}

	public void setParent(BTreeNode parent) {
		this.parent = parent;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return String.format("BTreeNode [value=%s, color=%s, depth=%s]", value,  color, depth);
	}
	
	
}
