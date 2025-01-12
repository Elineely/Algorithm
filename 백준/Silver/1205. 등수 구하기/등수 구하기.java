import java.util.*;
import java.io.*;

class Main
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int P;
	static int target;
	static StringBuilder sb = new StringBuilder();

	static int answer = -1;

	public static void main(String args[]) throws Exception
	{
		String line[] = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		target = Integer.parseInt(line[1]);
		P = Integer.parseInt(line[2]);
		
		if(N == 0){
			System.out.println("1");
			return;
		}
		
		int rank = 0;
		int prev = 2000000001;
		line = br.readLine().split(" ");
		if(N < P && Integer.parseInt(line[N - 1]) > target){
			System.out.println(N + 1);
			return;
		}

		for (int i = 0 ; i < N; ++i){
			int curr = Integer.parseInt(line[i]);
			if (curr != prev){
				rank = i + 1;
				if (curr <= target){
					if (N == P && Integer.parseInt(line[P - 1]) >= target)
						continue;
					answer = rank;
					break;
				}
				prev = curr;
			}//end of curr != prev
		}//end of for i
		System.out.println(answer);
	}//end of main
}//end of class
