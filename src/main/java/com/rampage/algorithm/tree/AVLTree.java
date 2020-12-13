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
		AVLTree tree = new AVLTree();
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.print();
		tree.insert(4);
		tree.insert(5);
		tree.insert(6);
		tree.insert(7);
		tree.print();
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
		node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
		leftNode.setHeight(Math.max(height(leftNode.getLeftChild()), height(node)) + 1);
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
		node.setRightChild(rightNode.getLeftChild());   
		if (rightNode.getLeftChild() != null) {
			rightNode.getLeftChild().setParent(node);
		}
        rightNode.setLeftChild(node);
        node.setParent(rightNode);
		
		// STEP2: 高度转换
		node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
		rightNode.setHeight(Math.max(height(rightNode.getRightChild()), height(node)) + 1);
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
	public void insert(int value) {
		this.root = insert(this.root, value);
	}
	
	private BTreeNode insert(BTreeNode curNode, int value) {
		if (curNode == null) {
			curNode = new BTreeNode(value);
			return curNode;
		}
		if (curNode.getValue() < value) {
            curNode.setRightChild(insert(curNode.getRightChild(), value));
            curNode.getRightChild().setParent(curNode);
			// 插入节点之后需要判断当前树是否失衡
			if (height(curNode.getRightChild()) - height(curNode.getLeftChild()) == 2) {
				if (value > curNode.getRightChild().getValue()) {
					// 右右失衡 左旋
					curNode = leftRotate(curNode);
				} else {
					curNode = rightLeftRotate(curNode);
				}
			}
		} else if (curNode.getValue() > value) {
            curNode.setLeftChild(insert(curNode.getLeftChild(), value));
            curNode.getLeftChild().setParent(curNode);
			if (height(curNode.getLeftChild()) - height(curNode.getRightChild()) == 2) {
				if (value < curNode.getLeftChild().getValue()) {
					curNode = rightRotate(curNode);
				} else {
					curNode = leftRightRotate(curNode);
				}
			}
		} else {
			throw new RuntimeException("树中已经存在值为：" + value + "的节点！");
		}
		curNode.setHeight(Math.max(height(curNode.getLeftChild()), height(curNode.getRightChild())) + 1);
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
	
	public BTreeNode remove(int value) {
		BTreeNode curNode;
		if ((curNode = search(value)) != null) {
			this.root = remove(this.root, value);
		}
		
		return curNode;
	}

	private BTreeNode remove(BTreeNode curNode, int value) {
		if (curNode == null) {
			return null;
		}
		if (value > curNode.getValue()) {
			// 需要往右删除
			curNode.setRightChild(remove(curNode.getRightChild(), value));
			// 删除右节点后失衡的旋转
			if (height(curNode.getLeftChild()) - height(curNode.getRightChild()) == 2) {
				BTreeNode left = curNode.getLeftChild();
				if (height(left.getRightChild()) > height(left.getLeftChild())) {
					curNode = leftRightRotate(curNode);
				} else {
					curNode = rightRotate(curNode);
				}
			}
		} else if (value < curNode.getValue()) {
			curNode.setLeftChild(remove(curNode.getLeftChild(), value));
			if (height(curNode.getRightChild()) - height(curNode.getLeftChild()) == 2) {
				BTreeNode right = curNode.getRightChild();
				if (height(right.getRightChild()) > height(right.getLeftChild())) {
					curNode = leftRotate(curNode);
				} else {
					curNode = leftRightRotate(curNode);
				}
			}
		} else {
			// 当前节点就是要删除的节点
			// 当前节点的左右子节点都不为空
			if (curNode.getLeftChild() != null && curNode.getRightChild() != null) {
				// 左子树比右子树高： 选择左子树的最大节点替换当前节点
				if (height(curNode.getLeftChild()) > height(curNode.getRightChild())) {
					BTreeNode leftMax = maximun(curNode.getLeftChild());
					curNode.setValue(leftMax.getValue());
					curNode.setLeftChild(remove(curNode.getLeftChild(), leftMax.getValue()));
				} else {
					// 右子树比左子树高或者相等，删除右子树最小的节点
					BTreeNode rightMin = minimun(curNode.getRightChild());
					curNode.setValue(rightMin.getValue());
					curNode.setRightChild(remove(curNode.getRightChild(), rightMin.getValue()));
				}
			} else {
				// 选择左右子节点不为空的替换该节点
				BTreeNode temp = curNode;
				curNode = curNode.getLeftChild() != null ? curNode.getLeftChild() : curNode.getRightChild();
				temp = null;
			}
		}
		
		return curNode;
	}
	
	private void print(BTreeNode tree, int value, int direction) {
        if(tree != null) {
            if(direction==0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.getValue(), value);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.getValue(), value, direction==1?"right" : "left");

            print(tree.getLeftChild(), tree.getValue(), -1);
            print(tree.getRightChild(),tree.getValue(),  1);
        }
    }

    public void print() {
        if (this.root != null)
            print(this.root, this.root.getValue(), 0);
    }
	
	private BTreeNode minimun(BTreeNode curNode) {
		if (curNode == null) {
			return null;
		}
		while (curNode.getLeftChild() != null) {
			curNode = curNode.getLeftChild();
		}
		return curNode;
	}

	private BTreeNode maximun(BTreeNode curNode) {
		if (curNode == null) {
			return null;
		}
		while (curNode.getRightChild() != null) {
			curNode = curNode.getRightChild();
		}
		return curNode;
	}

	public int height(BTreeNode node) {
		if (node == null) {
			return 0;
		}
		
		return node.getHeight();
	}
}
