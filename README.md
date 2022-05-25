**实验目的：**利用不同算法设计策略来求解组合优化问题。

**实验题目：** 0-1背包问题求解

假设有*n*个物品和一个背包，每个物品重量为*wi* (*i*=1,2,....,*n*)，价值为*vi* (*i*=1,2, ...., *n*)，背包最大容量为*C*，请问该如何选择物品才能使得装入背包中的物品总价值最大? 最大价值为多少？请按照如下要求完成算法：

(1) 请利用分治法来求解该问题，给出最优解值以及求解时间；

(2) 请利用动态规划算法来求解该问题，给出得到最优解值以及求解时间；

(3) 请利用贪心算法来求解该问题，给出得到的最优解值以及求解时间；

(4) 请利用回溯法来求解该问题，给出得到的最优解值以及求解时间；

(5) 请利用分支限界法来求解该问题，给出得到的最优解值以及求解时间；

(6) 请利用蒙特卡洛算法来求解该问题，给出得到的最优解值以及求解时间；

(7) （选做）请利用深度强化学习算法来求解该问题，给出最优解值以及求解时间。

(8) 给定相同输入，比较上述算法得到的最优解值和求解时间。当n比较大的时候，上述算法运算时间可能很长，请在算法中增加终止条件以确保在有限时间内找到最优解的值。