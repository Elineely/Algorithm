import java.util.*;
import java.io.*;

class Main
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static StringBuilder sb = new StringBuilder();

	static int[][] arr;

	public static void main(String args[]) throws Exception
	{
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];

		for(int i = 0 ; i < N; ++i){
			st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		for(int i = 0 ; i < N; ++i){
			int rank = 1;
			for(int k = 0; k < N; ++k){
				if (i == k)
					continue;
				if (arr[i][0] < arr[k][0] && arr[i][1] < arr[k][1])
					++rank;
			}
			sb.append(rank).append(" ");
		}
		System.out.println(sb.toString());
	}
}
