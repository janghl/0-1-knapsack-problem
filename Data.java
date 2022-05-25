
public class Data {

	public static int n = 20, c = 50;
	public static int[] v = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
	public static int[] w = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	public static void main(String[] args) {
		 DP.main();
		 divide_and_conquer.main();
		 greedy.main();
		 backtrack.main();
		 Bound.main();
		Monte_Carlo.main(10000000);				//哈哈，这时候差不多有解了
	}

}
