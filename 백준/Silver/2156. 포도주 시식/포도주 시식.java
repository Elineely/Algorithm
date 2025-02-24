import java.util.*;
import java.io.*;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N;

	static int[] glass;
	static int[] dp;
	public static void main(String[] args) throws IOException{
		N = sc.nextInt();
		glass = new int[N + 1];
		dp = new int[N + 1];

		for (int i = 1; i <= N; ++i){
			glass[i] = sc.nextInt();
		}
		if(N == 1){
			System.out.println(glass[1]);
			return;
		}
		dp[0] = 0;
		dp[1] = glass[1];
		dp[2] = glass[1] + glass[2];

		for(int i =  3; i <= N; ++i){
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + glass[i], dp[i - 3] + glass[i -1] + glass[i]));
		}

		System.out.println(dp[N]);
	}//end of main method
}//end of main class
