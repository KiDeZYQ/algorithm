package com.rampage.algorithm.basic;

import java.nio.ByteBuffer;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 临时测试的类
 * @author ziyuqi
 *
 */
public class TempTest {
	public static void main(String[] args) throws InterruptedException {
		/*ByteBuffer byteBuffer = ByteBuffer.allocate(8092);
		byteBuffer.clear();
		while (true) {
			Thread.sleep(10000);
		}*/
		
		// int[] arr = new int [Integer.MAX_VALUE - 1];
		
		
		SortedMap<Person, String> map = new TreeMap<>();
		map.put(new Person(), "1");
	}
	
	static class Person implements Comparable<Person> {

		@Override
		public int compareTo(Person o) {
			return 0;
		}
		
	}
}
