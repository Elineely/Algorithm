import java.util.*;
import java.io.*;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N;
	static int M;
	static List<Integer>[] edges;
	static Queue<Integer> q = new LinkedList<>();
	static int answer = 0;

	static Set<Integer> s = new HashSet<>();

	public static void main(String[] args) throws IOException{
		N = sc.nextInt();
		edges = new List[N + 1];
		for(int i = 1; i <= N; ++i){
			edges[i] = new LinkedList<Integer>();
		}

		M = sc.nextInt();
		for(int i = 0 ; i < M; ++i){
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			edges[v1].add(v2);
			edges[v2].add(v1);
		}
		s.add(1);
		// q.add(1);
		for(Integer e :  edges[1]){
			if(s.contains(e))
				continue;
			q.add(e);
			s.add(e);
			++answer;
		}
		
		while(!q.isEmpty()){
			int curr = q.poll();
			for(Integer e :  edges[curr]){
				if (s.contains(e))
					continue;
				s.add(e);
				++answer;
			}
		}

		System.out.println(answer);
	}//end of main method
}//end of main class
