package com.rampage.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * huffman树： 一种字符串压缩算法 基本原理是：
 * 利用各个字符出现的频率计算出对应的数组，然后每次从频率数组中选取出现频率最小的两个值作为树的叶子节点构建树，
 * 同时替换数组中这两个最小频率的数字为这两个频率相加的值。
 * 
 * @author kidezi
 *
 */
public class HuffmanTree {

    /**
     * 该算法的难点在于选择什么数据结构进行存储以及如何构造huffman树 这里有几点需要注意： 1.
     * 如果字符串的总共长度为n，则huffman树的总结点个数为2n
     * 
     * @param args
     */

    public void createTree(String str) {
        // STEP1: 计算每个数字出现的次数作为权重，并放入列表中
        int len = str.length();
        List<HuffData> huffDatas = new ArrayList<>();
        char ch = 0;
        HuffData newData = null;
        int index = -1;
        for (int i = 0; i < len; i++) {
            ch = str.charAt(i);
            newData = new HuffData(ch);
            index = huffDatas.indexOf(newData);
            if (index == -1) {
                huffDatas.add(newData);
            } else {
                huffDatas.get(index).increamentCount(); // 计数加1
            }
        }

        HuffmanNode[] nodes = new HuffmanNode[len * 2];
        // STEP2: 叶子节点初始化 这里需要注意和一般的数组构造树的情况不同，叶子节点反而是在数组最前面
        /**
         * 这一步的关键在于该数组中的叶子节点不需要保证有序的，实际上我们直接按照字符统计的顺序即可，关键之处是直接将权重存到节点属性
         */
        for (int i = 0; i < 2 * len - 1; i++) {// 初始化
            nodes[i] = new HuffmanNode();
            /* 前n个结点为叶子结点 */
            if (i < len) {
                nodes[i].setWeight(huffDatas.get(i).count);
                nodes[i].setData(huffDatas.get(i).data);
            } else {
                nodes[i].setWeight(-1);
                nodes[i].setData('-');
            }
            nodes[i].setParent(-1);
            nodes[i].setlChild(-1);
            nodes[i].setrChild(-1);
        }

        // STEP3: 依次选取权重最小的叶子节点，然后构造树（其实就是指定其父节点）
        /**
         * 这一步的技巧其实同上一步，依次将最小的两个节点的父节点存储在下标为 n、n+1....2n-1的元素中
         */
        int startIndex = 0;
        for (int i = len; i < 2 * len - 1; i++) {
            // STEP3.1 找到最小的两个节点
            int min1Idx = -1;
            int min2Idx = -1;
            int min1Weight = len + 1;
            int min2Weight = len + 1;
            int curWeight = -1;
            for (int j = startIndex; j < i; j++) {
                curWeight = nodes[j].getWeight();
                if (curWeight < min1Weight) {
                    min1Idx = j;
                    min1Weight = curWeight;
                } else if (nodes[j].getWeight() < min2Weight) {
                    min2Idx = j;
                    min2Weight = curWeight;
                }
            }

            // STEP3.2: 分别将i节点的权重设置为该两个最小节点相加且设置i节点为最小权重两节点的父节点
            nodes[i].setWeight(min1Weight + min2Weight);
            nodes[i].setlChild(min1Idx);
            nodes[i].setrChild(min2Idx);
            nodes[i].setData('+');

            nodes[min1Idx].setParent(i);
            nodes[min2Idx].setParent(i);
            startIndex += 2; // 下次查找最小的两个节点，只能从下标为2开始
        }
    }

    private static class HuffData implements Comparable<HuffData> {
        private char data; // 数据的值

        private int count = 1; // 数据的计数

        public HuffData(char data) {
            this.data = data;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + data;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            HuffData other = (HuffData) obj;
            if (data != other.data)
                return false;
            return true;
        }

        public void increamentCount() {
            this.count++;
        }

        @Override
        public int compareTo(HuffData o) {
            return this.count - o.count;
        }
    }

    public static void main(String[] args) {

    }
}
