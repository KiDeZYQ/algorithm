# algorithm
algorithm







































































































# 附录

## Undirected Graph1

![image](https://github.com/KiDeZYQ/algorithm/blob/master/images/undirectedGraph.png)

## Undirected Weighted Graph2

![image](https://github.com/KiDeZYQ/algorithm/blob/master/images/undirectedWeightGraph.png)

## RR Lose Balance

![1576490508661](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1576490508661.png)

​	节点1右右失衡， 需要将节点1左旋，最终结果如下：

![1576490530054](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1576490530054.png)



## LL Lose Balance

![1576490670370](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1576490670370.png)

​	节点1左左失衡，需要将节点1右旋，最终结果如下：

![1576490690138](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1576490690138.png)



## LR Lose Balanace

![1576491489862](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1576491489862.png)

​	节点0左右失衡，我们首先将L失衡（1）解决掉，解决的办法为将2右旋：

![1576494204031](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1576494204031.png)

右旋之后，再将原来的最高的节点0左旋，得到最终平衡树：

![1576494669881](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1576494669881.png)



## RL失衡

​	与上面的LR失衡相反，平衡方法也正好相反，先左旋再右旋