import java.util.*;

public class Bound {
	static boolean debug = false;

	public static class Status {
		int bound;
		boolean select;
		int current_price;
		int current_weight;
		int layer;
		int path;

		public Status(int bound, boolean select, int current_price, int current_weight, int layer, int path) {
			this.bound = bound;
			this.select = select;
			this.current_price = current_price;
			this.current_weight = current_weight;
			this.layer = layer;
			this.path = path;
		}

	}

	public static void init() {
		for (int index = 2; index <= Data.n; index++) {// 外层向右的index，即作为比较对象的数据的index
			int ptemp = p[index];// 用作比较的数据
			int qtemp = q[index];
			int leftindex = index - 1;
			while (leftindex >= 1 && (float) p[leftindex] / q[leftindex] < (float) ptemp / qtemp) {// 当比到最左边或者遇到比temp小的数据时，结束循环
				p[leftindex + 1] = p[leftindex];
				q[leftindex + 1] = q[leftindex];
				leftindex--;
			}
			p[leftindex + 1] = ptemp;// 把temp放到空位上
			q[leftindex + 1] = qtemp;
		}
	}

	public static class reverse_ord implements Comparator<Status> {
		@Override
		public int compare(Status x, Status y) {
			return x.current_price > y.current_price ? -1 : (x.current_price == y.current_price ? 0 : 1);
		}
	}

	static PriorityQueue<Status> myQueue = new PriorityQueue<>(new reverse_ord());

	static int[] p = new int[Data.n + 1];
	static int[] q = new int[Data.n + 1];
	static int[] x = new int[Data.n];
	static int cw = 0, cp = 0; // cw为当前装包重量，cp为当前装包价值
	static Status N;
	static int result = 0;
	static int current_path;

	public static int Bound(int i) {
		// 计算结点所对应的价值的上界
		int cleft = Data.c - cw; // 剩余背包容量
		int b = cp; // 价值上界
		// 以物品单位重量价值递减顺序装填剩余容量
		while (i <= Data.n && q[i] <= cleft) {
			cleft -= q[i]; // q[i]表示i物品的重量
			b += p[i]; // p[i]表示i物品的价值
			i++;
		}
		// 装填剩余容量装满背包
		if (i <= Data.n)
			b += p[i] / q[i] * cleft;
		return b;
	}

	public static void knapsack() {

		// 优先队列式分支限界法，返回最大价值，n为物品数目，c为背包容量，w为物品重量，p为物品价值
		// 算法开始之前，已经按照物品单位价值率按照降序顺序排列好了
		int bestp = 0; // 当前最优值
		int i = 1;
		int up = Bound(1); // 函数Bound(i)计算当前结点相应的价值上界
		while (i != Data.n + 1) { // 非叶子结点
			// 首先检查当前扩展结点的左儿子结点为可行结点
			if (cw + q[i] <= Data.c) { // 左孩子结点为可行结点
				if (debug)
					System.out.print("left: " + current_path + " \n");
				if (cp + p[i] > bestp)
					bestp = cp + p[i];
				Status left_node = new Status(up, true, cp + p[i], cw + q[i], i + 1, current_path * 2 + 1); // 将左孩子结点插入到优先队列中
				myQueue.add(left_node);
				cp += p[i];
				cw += q[i];
			}
			up = Bound(i + 1);
			// 检查当前扩展结点的右儿子结点
			if (up >= bestp) // 右子树可能包含最优解
			{
				if (debug)
					System.out.print("right: " + current_path + " \n");
				Status right_node = new Status(up, false, cp, cw, i + 1, current_path * 2);
				myQueue.add(right_node);
			}
			// 将右孩子结点插入到优先队列中
			// 从优先级队列（堆数据结构）中取下一个扩展结点N
			N = myQueue.poll();
			i = N.layer;
			current_path = N.path;
			if (debug)
				System.out.print(i + " " + current_path + " \n");
		}
		return;
	}

	public static void main() {
		long begintime = System.currentTimeMillis();
		p[0] = q[0] = 0;
		for (int i = 1; i <= Data.n; i++) {
			p[i] = Data.v[i - 1];
			q[i] = Data.w[i - 1];
		}
		init();
		if (debug) {
			for (int i = 1; i <= Data.n; i++)
				System.out.print(" " + p[i]);
			System.out.println();
			for (int i = 1; i <= Data.n; i++)
				System.out.print(" " + q[i]);
			System.out.println();
		}
		knapsack();
		System.out.println("total value: " + N.current_price);
		for (int i = Data.n - 1; i >= 0; i--) {
			x[i] = N.path % 2;
			N.path >>= 1;
		}
		for (int i : x)
			System.out.print(i + " ");
		long endtime = System.currentTimeMillis();
		long costTime = (endtime - begintime);
		System.out.println();
		System.out.println("costTime: " + costTime + " method: Bound");
	}

}
