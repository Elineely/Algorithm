import java.util.*;
import java.io.*;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N;
	static int Q;
	static List<int[]>[] edge;
	public static void main(String[] args) throws IOException{
		N = sc.nextInt();
		Q = sc.nextInt();
		edge = new ArrayList[N + 1];
		for(int i = 1 ; i < N + 1; ++i){
			edge[i] = new ArrayList<>();
		}


		for(int i = 0; i < N - 1; ++i){
			int video1 = sc.nextInt();
			int video2 = sc.nextInt();
			int usado = sc.nextInt();
			edge[video1].add(new int[]{video2, usado});
			edge[video2].add(new int[]{video1, usado});

		}

		StringBuffer  sb = new StringBuffer();
		for(int i = 0; i < Q; ++i){
			int limit = sc.nextInt();
			int start = sc.nextInt();
			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[N + 1]; 
			q.add(start);
			visited[start] = true;

			int ans = 0;
			while(!q.isEmpty()){
				int cur = q.poll();

				for(int [] a : edge[cur]){
					if(!visited[a[0]] && a[1] >= limit){
						q.add(a[0]);
						visited[a[0]] = true;
						ans++;
					}
				}
			}
			sb.append(ans).append('\n');
		}
		System.out.print(sb);
	
	}//end of main method
}//end of main class
