import java.util.*;
public class Monte_Carlo {

	static int maxvalue = 0;
	static int current_weight = 0;
	static int current_value = 0;
	static int[] temp = new int[Data.n];
	static int[] x = new int[Data.n];
	static int[][] m = new int[Data.n][Data.c + 1];

	public static void knapsack(int search) {
		Random rand =new Random();
		for(int times=0;times<search;times++)
		{
			current_weight = 0;
			current_value = 0;
			for(int i=0;i<Data.n;i++)
				
				if((temp[i]=rand.nextInt(2))==1)
					{current_weight+=Data.w[i];
					current_value+=Data.v[i];
					}
				if(current_weight<Data.c&&current_value>maxvalue)
				{
					maxvalue=current_value;
					for(int j=0;j<Data.n;j++)
						x[j]=temp[j];
				}
			
		}
		return;
		
	}

	public static void main(int search) {
		long begintime = System.currentTimeMillis();
		knapsack(search);
		System.out.println("total value: " + maxvalue);
		for (int i : x)
			System.out.print(i + " ");
		long endtime = System.currentTimeMillis();
		long costTime = (endtime - begintime);
		System.out.println();
		System.out.println("costTime: " + costTime + "   method: Monte_Carlo");
	}
}
