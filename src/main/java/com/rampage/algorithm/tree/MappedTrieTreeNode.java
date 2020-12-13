package com.rampage.algorithm.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * map类型的节点
 * @author kidezi
 *
 */
public class MappedTrieTreeNode<T> {
    private int count;
    
    private Map<T, MappedTrieTreeNode<T>> child;

    public MappedTrieTreeNode() {
        count = 1;
        child = new HashMap<>();
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Map<T, MappedTrieTreeNode<T>> getChild() {
        return child;
    }

    public void setChild(Map<T, MappedTrieTreeNode<T>> child) {
        this.child = child;
    }
    
    public void increamentCount() {
        this.count++;
    }
}
