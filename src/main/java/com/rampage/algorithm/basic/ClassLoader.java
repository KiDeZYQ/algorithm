package com.rampage.algorithm.basic;

import sun.reflect.Reflection;

/**
 * ClassLoader探究的相关类
 * @author ziyuqi
 *
 */
public class ClassLoader {
	public static void main(String[] args) throws ClassNotFoundException {
		// 默认会调用ClassLoader#loadClass(name, false)  不会执行static代码块
		ClassLoader.class.getClassLoader().loadClass("com.rampage.algorithm.basic.Animal");
		
		// 默认执行 Class#forName0(className, true, ClassLoader.getClassLoader(caller), caller);  会执行static代码块
		// Class.forName("com.rampage.algorithm.basic.Animal");
		// 如果想不初始化类，可以用如下方式 此时也不会执行static代码块
		Class.forName("com.rampage.algorithm.basic.Animal", false, ClassLoader.class.getClassLoader());
		
		System.out.println(ClassLoader.class.getClassLoader());  // sun.misc.Launcher$AppClassLoader@73d16e93
		System.out.println(ClassLoader.class.getClassLoader().getParent());  // sun.misc.Launcher$ExtClassLoader@15db9742
		System.out.println(ClassLoader.class.getClassLoader().getParent().getParent());  // null
	}
}
