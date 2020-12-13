package com.rampage.algorithm.tree;

/**
 * 字典树   
 * 又叫DictTree  采用空间换时间的做法，是哈希树的优化版本
 * @author ziyuqi
 *
 */
public class TrieTree {

    /**
     * 定义根节点，哨兵模式 root没有什么意义
     */
    private TrieTreeNode root;
    
    private int childLen;
    
    private MappedTrieTreeNode<Character> newRoot;
    
    public TrieTree() {
        newRoot = new MappedTrieTreeNode<>();
    }
    
    public TrieTree(int childLen) {
        this.childLen = childLen;
        root = new TrieTreeNode(childLen);
    }
    
    /**
     * 第一种实现方式addNode和seerchNode的时间复杂度都是O(len) 其中len为字符串的长度，
     *  但是这种牺牲了大量空间，每个节点都需要预先分配26个子节点数组以便存放所有可能子节点
     *  因此应该有一种方式来实现空间优化，即子节点通过列表的方式实现查找 这样会影响效率，
     *  所以可能考虑将子节点放入map中，其中key为对应节点的值
     *  
     *  
     *  第二种实现见addNodeNew 、 searchNodeNew
     * 
     * 
     */
    
    public void addNodeNew(String str) {
        char ch = 0;
        MappedTrieTreeNode<Character> curNode = newRoot;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (curNode.getChild().get(ch) == null) {
                MappedTrieTreeNode<Character> newNode = new MappedTrieTreeNode<Character>();
                curNode.getChild().put(ch, newNode);
            } else {
                curNode.getChild().get(ch).increamentCount();
            }
            curNode = curNode.getChild().get(ch);
        }
    }
    
    public int searchNodeNew(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        
        char ch = 0;
        MappedTrieTreeNode<Character> curNode = newRoot;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (curNode.getChild().get(ch) == null) {
                return 0;
            }
            
            curNode = curNode.getChild().get(ch);
        }
        return curNode.getCount();
    }
    
    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.addNodeNew("aaa");
        tree.addNodeNew("aab");
        tree.addNodeNew("aac");
        System.out.println(tree.searchNodeNew("aa"));
        System.out.println(tree.searchNodeNew("aac"));
    }
    
    /**
     * 添加字符串到字典树
     * @param str 
     */
    public void addNode(String str) {
        char ch = 0;
        int offSet = -1;
        TrieTreeNode curNode = root;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            offSet = (ch - 'a');
            if (curNode.getChild()[offSet] == null) {
                curNode.getChild()[offSet] = new TrieTreeNode(childLen);
            } else {
                curNode.getChild()[offSet].increamentCount();
            }
            curNode.setCh(ch);
            curNode = curNode.getChild()[offSet];
        } 
    }
    
    
    /**
     * 查找字符串
     * @param str  待查找的字符串
     * @return 字符串出现的次数，如果字符串不存在则返回0 注意这里不要求字符串是最终字符串
     * 即如果树中有aab, 则查找a或者aa也都能找到
     */
    public int search(String str) {
        if (str == null || str.isEmpty()) {
            return -1;      // 非法字符串直接返回-1
        }
        
        TrieTreeNode curNode = root;
        char ch = 0;
        int offSet = -1;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            offSet = ch - 'a';
            if (curNode.getChild()[offSet] == null) {
                return 0;
            }
            
            curNode = curNode.getChild()[offSet];
            
        }
        
        return curNode.getnCount();
    }
    
}
