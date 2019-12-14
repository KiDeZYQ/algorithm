package com.rampage.algorithm.classical;

/**
 * 最长回文子串的马拉车算法
 * @author KiDe
 *
 */
public class Manacher {
	public static void main(String[] args) {
		String str = "aabccbaadse";
		System.out.println(manacher(str));
	}

	private static int manacher(String str) {
		/**
		 * 马拉车算法求最长回文子串的长度思想：
		 * 	首先给每个字符串前后都补上字符串中不存在的字符，这里补上# 使得最终字符串的长度为奇数
		 *  其次为了计算方便，最前面给上一个特殊字符
		 *  算法的核心思想：
		 *  	先定义一个数组p，其中p[i]表示以点i为中心的最长回文子串长度
		 *  如果按照原来的最古老的办法，就是从i开始依次向左右两边比较字符
		 *  我们假设现在我们要求i点的最长回文串长度 最理想的算法是像动态规划一样我们可以利用前面求得的结果推后面的结果
		 *  我们现在假设存在一个点的下标为pivot，我们以pivot为中心向右延伸的距离最大，即在所有p[0]到p[i]中
		 *  p[pivot] + pivot最大 ( 0 <= pivot < i)
		 *  接下来我们就讨论，i如何利用这个pivot来实现快速查找，其实很简单，当i不再p[pivot] + pivot的范围内的时候（即i  > p[pivot] + pivot）
		 *  此时只有按原来暴力法来求解
		 *  但是当i在范围内的时候，则p[i] = min(p[j], p[pivot] + pivot - i) 其中j为i关于pivot的对称点(2*pivot - i)
		 *  于是有下面算法
		 */
		char[] newStr = new char[str.length()*2 + 2];
		newStr[0] = '~';
		newStr[1] = '#';
		for (int i = 0, len = str.length(); i < len; i++) {
			newStr[(i+1) * 2] = str.charAt(i);
			newStr[(i+1) * 2 + 1] = '#';
		}
		
		int[] p = new int[newStr.length];
		p[0] = 0;
		p[1] = 1;
		int mx = 0;		// 这里用mx表示 p[pivot] + pivot
		int pivot = 0;
		int max = 1;
		for (int i = 2, len = newStr.length; i < len; i++) {
			if (i < mx) {
				p[i] = Math.min(mx - i, p[pivot * 2 - i]);
			} else {
				p[i] = 1;
			}
			
			// 如果再往前后遍历匹配则p[i]++
			while ((i + p[i]) < len && (i - p[i] >= 0) && newStr[i - p[i]] == newStr[i + p[i]]) {
				p[i]++;
			}
			
			// 更新最大的pivot
			if (p[i] + i > mx) {
				pivot = i;
				mx = p[i] + i;
			}
			
			max = Math.max(max, p[i] - 1);
		}
		return max;
	}
}
