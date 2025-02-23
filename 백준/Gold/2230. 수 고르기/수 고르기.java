import java.util.*;
import java.io.*;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N;
	static int M;
	static int[] A;
	static int ans = Integer.MAX_VALUE; 
	public static void main(String[] args) throws IOException{
		N = sc.nextInt();
		M = sc.nextInt();
		A = new int[N];
		for(int i = 0 ; i < N; ++i){
			A[i] = sc.nextInt();
		}
		Arrays.sort(A);

		int left = 0;
		int right = 0;
		while (left < N){
			int diff = A[right] - A[left];
			if (diff < M && right == N - 1)
				break;
			if (right < N - 1 && diff < M)
				right++;
			else if (diff == M){
				ans = M;
				break;
			}
			else if (diff > M){
				left++;
				if (diff < ans)
					ans = diff;
			}
		}
		System.out.println(ans);
	}//end of main method
}//end of main class
