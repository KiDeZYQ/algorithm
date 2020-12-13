package com.rampage.algorithm.tree;

/**
 * huffman树的节点定义
 * @author kidezi
 *
 */
public class HuffmanNode {
    /**
     * 实际数据，这里都作为char
     */
    private char data;
    
    /**
     * 权重
     */
    private int weight;
    
    /**
     * 编码
     */
    private String code; 
    
    /**
     * 分别表示节点的左子树、右子树、父节点在数组中的下标
     */
    private int lChild, rChild, parent;

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getlChild() {
        return lChild;
    }

    public void setlChild(int lChild) {
        this.lChild = lChild;
    }

    public int getrChild() {
        return rChild;
    }

    public void setrChild(int rChild) {
        this.rChild = rChild;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
