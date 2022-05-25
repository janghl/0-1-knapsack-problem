public class DP {

	static int debug = 0;

	static int[] x = new int[Data.n];
	static int[][] m = new int[Data.n][Data.c + 1];

	public static void knapsack(int k, int p, int[][] m) {
		// 其最优值为m(i, j)，即m(i, j)是背包容量为j，可选物品为i, i+1, …, p的0-1背包问题的最优值。
		int jMax = Data.w[p - 1] - 1 < k ? Data.w[p - 1] - 1 : k;
		for (int j = 0; j <= jMax; j++) // 装不下第一件
			m[p - 1][j] = 0;
		for (int j = Data.w[p - 1]; j < k; j++) // 能装下，放入第一件
			m[p - 1][j] = Data.v[p - 1];
		for (int i = p - 2; i > 0; i--) {
			jMax = Data.w[i] - 1 < k ? Data.w[i] - 1 : k;
			for (int j = 0; j <= jMax; j++)
				m[i][j] = m[i + 1][j];
			for (int j = Data.w[i]; j <= k; j++)
				m[i][j] = m[i + 1][j] > m[i + 1][j - Data.w[i]] + Data.v[i] ? m[i + 1][j]
						: m[i + 1][j - Data.w[i]] + Data.v[i];
		}
		if (k >= Data.w[0])
			m[0][k] = m[1][k] > m[1][k - Data.w[0]] + Data.v[0] ? m[1][k] : m[1][k - Data.w[0]] + Data.v[0];
		else
			m[0][k] = m[1][k];
	}

	public static void traceback(int k, int p, int[][] m, int[] x) {
		for (int i = 0; i < p - 1; i++) {
			if (m[i][k] == m[i + 1][k])
				x[i] = 0;
			else {
				x[i] = 1;
				k -= Data.w[i];
			}
			x[p - 1] = m[p - 1][k] != 0 ? 1 : 0;
		}
	}

	public static void main() {
		long begintime = System.currentTimeMillis();
		knapsack(Data.c, Data.n, m);
		traceback(Data.c, Data.n, m, x);
		System.out.println("total value: " + m[0][Data.c]);
		if (debug == 1) {
			for (int i = 0; i < Data.n; i++) {
				for (int j = 0; j < Data.c; j++)
					System.out.print(m[i][j] + " ");
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}
		for (int i : x)
			System.out.print(i + " ");
		long endtime = System.currentTimeMillis();
		long costTime = (endtime - begintime);
		System.out.println();
		System.out.println("costTime: " + costTime + "   method: DP");
	}

}
