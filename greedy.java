import java.util.*;

public class greedy {
	public static void main() {
		long begintime = System.currentTimeMillis();
		int weight = 0, value = 0;
		int[] x = new int[Data.n];
		for (int i : x)
			i = 0;
		TreeMap<Integer, Integer> mymap = new TreeMap<>(Comparator.reverseOrder());
		for (int i = 0; i < Data.n; i++) // 为啥这里这么慢？
			mymap.put(Data.v[i], Data.w[i]);
		for (Map.Entry<Integer, Integer> entry : mymap.entrySet()) {
			if (weight + entry.getValue() <= Data.c) {
				// System.out.println("entry.getKey() " + entry.getKey()+" weight " + weight);
				weight += entry.getValue();
				value += entry.getKey();
				for (int temp = 0; temp < Data.n; temp++)
					if (Data.v[temp] == entry.getKey() && Data.w[temp] == entry.getValue())
						x[temp] = 1;

			} else
				break;
		}

		System.out.println("total value: " + value);
		for (int i : x)
			System.out.print(i + " ");
		long endtime = System.currentTimeMillis();
		long costTime = (endtime - begintime);
		System.out.println();
		System.out.println("costTime: " + costTime + "   method: greedy");
	}
}
