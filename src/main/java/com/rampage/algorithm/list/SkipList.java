package com.rampage.algorithm.list;

import java.util.Random;

/**
 * 跳跃表
 * @author ziyuqi
 *
 */
public class SkipList<T> {
	/**
	 * 头节点 最上层的最左节点
	 */
	private SkipNode<T> head;
	
	/**
	 * 尾节点
	 */
	private SkipNode<T> tail;
	
	/**
	 * 当前最大等级，从0开始
	 */
	private int level;
	
	/**
	 * 随机数产生器
	 */
	private Random rand;
	
	
	/**
	 * 晋升概率 1 - 10
	 */
	private static final int UP_PROB = 5;
	
	private static final int MAX_INT = 10;
	
	/**
	 * 总的节点个数
	 */
	private int count;		
	
	/**
	 * 实际节点个数：不包含跳表晋升的新节点
	 */
	private int size;
	
	public SkipList() {
		rand = new Random();
		head = new SkipNode<T>(Integer.MIN_VALUE, null);
		tail = new SkipNode<T>(Integer.MAX_VALUE, null);
		head.setNext(tail);
		tail.setPre(head);
	}
	
	/**
	 * 查找节点 如果找到key相同的，则返回找到的第一个节点，否则返回不大于待查找key的最大节点
	 * @param key 待查找的节点的key
	 * @return
	 */
	public SkipNode<T> findNode(int key) {
		SkipNode<T> p = head;
		while (true) {
			while (p.getNext() != null && p.getNext().getKey() <= key) {
				p = p.getNext();
			}
			
			if (p.getDown() != null) {
				p = p.getDown();
			} else if (p.getNext() != null && p.getNext().getKey() > key) {
				break;
			}
		}
		
		return p;
	}
	
	public SkipNode<T> get(int key) {
		SkipNode<T> p = findNode(key);
		if (p.getKey() == key) {
			return p;
		}
		
		return null;
	}
	
	public T remove(int key) {
		SkipNode<T> delNode = get(key);
		if (delNode == null) {
			return null;
		}
		T oldVal = delNode.getValue();
		SkipNode<T> next = null;
		
		// 因为此时找到的delNode是最后一层的节点，如果上层还有节点也需要再删除掉
		while (delNode != null) {
			next = delNode.getNext();
			next.setPre(delNode.getPre());
			delNode.getPre().setNext(next);
			delNode = delNode.getUp();
		}
		
		return oldVal;
	}
	
	public void put(int key, T value) {
		SkipNode<T> p = findNode(key);	// 找到待插入节点的前置节点（如果存在key相同的，则放在第一个key相同的元素后面）
		SkipNode<T> insertNode = new SkipNode<T>(key, value);
		insertNode(p, insertNode);		// 在当前层插入节点
		count++; 
		size++;
		
		// 判断是否需要晋升
		int curLevel = 0;
		while (rand.nextInt(MAX_INT) + 1 > UP_PROB) {
			if (curLevel >= this.level) {
				addEmptyLevel();
				System.out.println("提升");
			}
			
			while (p.getUp() == null) {
				p = p.getPre();
			}
			p = p.getUp();
			//  创建p上一层的影子节点，节点只存储key不存储value
			SkipNode<T> shadow = new SkipNode<T>(key, null);
			insertNode(p, shadow);
			shadow.setDown(insertNode);
			insertNode.setUp(shadow);
			insertNode = shadow;
			curLevel++;
			count++; 
		}
		
	}

	private void addEmptyLevel() {
		SkipNode<T> p = new SkipNode<T>(Integer.MIN_VALUE, null);
		SkipNode<T> q = new SkipNode<T>(Integer.MAX_VALUE, null);
		p.setNext(q);
		q.setPre(p);
		p.setDown(head);
		q.setDown(tail);
		head.setUp(p);
		tail.setUp(q);
		head = p;
		tail = q;
		this.level++;
	}

	/**
	 * 将新节点插入指定节点后面
	 * @param p	指定节点
	 * @param insertNode 新的待插入的节点
	 */
	private void insertNode(SkipNode<T> p, SkipNode<T> insertNode) {
		insertNode.setNext(p.getNext());
		insertNode.setPre(p);
		p.getNext().setPre(insertNode);
		p.setNext(insertNode);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(1024);
		SkipNode<T> curHead = this.head;
		SkipNode<T> p = curHead;
		boolean start = true;
		while (curHead != null) {
			if (!start) {
				sb.append("--->");
			} else {
				start = false;
			}
			sb.append("(").append(p.getKey()).append(", ").append(p.getValue()).append(")");
			p = p.getNext();
			if (p == null) {
				start = true;
				sb.append("\n");
				curHead = curHead.getDown();
				p = curHead;
			}
		}
		sb.append("\n");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		SkipList<String> skipList = new SkipList<>();
		skipList.put(0, "aaaa");
		System.out.println(skipList);
		skipList.put(3, "bbbb");
		System.out.println(skipList);
		skipList.put(2, "cccc");
		System.out.println(skipList);
		skipList.put(4, "dddd");
		System.out.println(skipList);
	}
}
