package com.rampage.algorithm.list;

/**
 * 跳跃表中的节点
 * @author KiDe
 *
 */
public class SkipNode<T> {
	/**
	 * 前面一个节点
	 */
	private SkipNode<T> pre;
	
	/**
	 * 后面一个节点
	 */
	private SkipNode<T> next;
	
	/**
	 * 下面一个节点
	 */
	private SkipNode<T> down;
	
	/**
	 * 上面一个节点
	 */
	private SkipNode<T> up;
	
	/**
	 * 节点key
	 */
	private int key;
	
	/**
	 * 节点值
	 */
	private T value;
	
	public SkipNode(int key, T value) {
		this.key = key;
		this.value = value;
	}

	public SkipNode<T> getPre() {
		return pre;
	}

	public void setPre(SkipNode<T> pre) {
		this.pre = pre;
	}

	public SkipNode<T> getNext() {
		return next;
	}

	public void setNext(SkipNode<T> next) {
		this.next = next;
	}

	public SkipNode<T> getDown() {
		return down;
	}

	public void setDown(SkipNode<T> down) {
		this.down = down;
	}

	public SkipNode<T> getUp() {
		return up;
	}

	public void setUp(SkipNode<T> up) {
		this.up = up;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
