import java.util.*;
import java.io.*;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static int[] lines;

	static int N ;
	static int K;

	public static void main(String[] args) throws IOException{
		K = sc.nextInt();
		N = sc.nextInt();
		lines = new int[K];

		int max = -1;
		for(int i = 0 ; i < K; ++i){
			lines[i] = sc.nextInt();
			if (lines[i] > max)
				max = lines[i];
		}

		long ans = findMaxLength(max);

		System.out.println(ans);
	}//end of main method

	static long findMaxLength(int max){
		long ans = 1;

		long left = 1;
		long right = max;
		long mid;
		while (true){
			if(left > right)
				break;
			mid = (left + right) / 2;
			if (isMoreThanN(mid)){
				ans = mid;
				left = mid + 1;
			}
			else{
				right = mid - 1;
			}
		}

		return ans;
	}//end of find method

	static boolean isMoreThanN(long length){
		int amount = 0;
		for(int l : lines){
			amount += l / length;
		}
		return amount >= N ? true : false;
	}

}//end of main class