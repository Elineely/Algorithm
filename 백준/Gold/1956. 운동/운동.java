import java.util.*;
import java.io.*;

class Main
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int V;
	static int E;
	// static long minDistance = Long.MAX_VALUE; --> long MAX_VALUE 사용시 
    //두 값을 더하는 연산에서 오버플로우로 MIN_VALUE에 가까운 작은 값이 들어가게 되어서 연산이 제대로 되지 않는다.
	static long[][] Edges;
	static final long INF = 400 * 10000 + 1; 
 
	public static void main(String args[]) throws Exception
	{
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		Edges = new long[V + 1][V + 1];

		for (int i = 1 ; i <= V; ++i ){
			for(int k = 1; k <= V; ++k){
				if (i != k)
					Edges[i][k] = INF;
			}
		}

		for (int i = 0; i < E; ++i){
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			Edges[a][b] = c;
		}

		for(int i = 1; i <= V ; ++i){
			for(int k = 1; k <= V; ++k){
				for (int l = 1; l <=V ;  ++l){
					if (l == k)
						continue;
					if (Edges[k][l] > Edges[k][i] + Edges[i][l]){
						Edges[k][l] = Edges[k][i] + Edges[i][l];
					}
				}
			}
		}

		long minDistance = INF;
		for(int i = 1; i <= V; ++i){
			for(int k = 1; k <= V; ++k){
				if (i == k){
					continue;
				}
				if (Edges[i][k] != INF && Edges[k][i] != INF){
				    minDistance = Math.min(minDistance, Edges[k][i] + Edges[i][k]);
				}
			}
		}

		if (minDistance == INF)
			minDistance = -1;

		System.out.println(minDistance);
	}//end of main method

}//end of class
