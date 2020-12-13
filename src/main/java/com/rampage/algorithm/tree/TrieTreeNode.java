package com.rampage.algorithm.tree;

/**
 * 字典树节点，这里假设
 * 
 * @author kidezi
 *
 */
public class TrieTreeNode {

    private int nCount; // 记录该字符出现次数

    private char ch; // 记录该字符

    private TrieTreeNode[] child; // 记录子节点

    /**
     * 构造方法，需要传入不同子节点的可能个数 对于字符串存储的，子节点可能为26个字母中的任意一个，所以此时diffCount为26
     * 
     * @param diffCount
     *            子节点可能的不同个数
     */
    public TrieTreeNode(int diffCount) {
        nCount = 1;
        child = new TrieTreeNode[diffCount];
    }

    public int getnCount() {
        return nCount;
    }

    public void setnCount(int nCount) {
        this.nCount = nCount;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public TrieTreeNode[] getChild() {
        return child;
    }

    public void setChild(TrieTreeNode[] child) {
        this.child = child;
    }
    
    public void increamentCount() {
        this.nCount++;
    }
}
