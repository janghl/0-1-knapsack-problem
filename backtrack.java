
public class backtrack {
	static int expect_most_weight = 0;
	static int expect_most_value = 0;
	static int select = 0;
	static int not_select = 0;
	static int maxvalue = 0;
	static int count = 0;
	static int result = 0;
	static int[] x = new int[Data.n];
	static int[][] m = new int[Data.n][Data.c + 1];

	public static int knapsack(int k, int p, boolean choose, int value) {
		if (p == 0) {
			if (value > maxvalue && k >= 0) {
				result = count;
				maxvalue = value;
			}
			count++;
			return choose ? Data.v[Data.n - 1] : 0;
		}
		for (int i = Data.n - 1; i >= Data.n - p; i--) // 子树价值太少或超重，剪枝
			expect_most_value += Data.v[i];
		if (maxvalue < expect_most_value + value - Data.v[Data.n - p] && k > 0) // 如果不选，要减掉这一层的value
			not_select = knapsack(k, p - 1, false, value);
		if (maxvalue < expect_most_value + value && k > 0)
			select = knapsack(k - Data.w[Data.n - p], p - 1, true, value + Data.v[Data.n - p]);
		return select > not_select ? select : not_select;
	}

	public static void main() {
		long begintime = System.currentTimeMillis();
		knapsack(Data.c, Data.n, true, 0);
		System.out.println("total value: " + maxvalue);
		for (int i = Data.n - 1; i >= 0; i--) {
			x[i] = result % 2;
			result >>= 1;
		}
		for (int i : x)
			System.out.print(i + " ");
		long endtime = System.currentTimeMillis();
		long costTime = (endtime - begintime);
		System.out.println();
		System.out.println("costTime: " + costTime + "   method: backtrack");
	}
}
