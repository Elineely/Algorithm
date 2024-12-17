import java.util.*;
import java.io.*;

class Main
{
	// static class Node{
	// 	int number;
	// 	int edgeInCount;

	// 	Node(int n , int e){
	// 		number = n;
	// 		edgeInCount = e;
	// 	}
	// }

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuffer sb = new StringBuffer();

	static int N;
	static int M;

	// static PriorityQueue<Node> nodes = new PriorityQueue<Node>((o1, o2)-> {
	// 	if(o1.edgeInCount == o2.edgeInCount){
	// 		return o1.number - o2.number;
	// 	}
	// 	return o1.edgeInCount - o2.edgeInCount;
	// });  --> 매번 edgeInCount 를 갱신 할 수 없으므로 pq 는 사용 불가하다.

	static int[] EdgeIn;
	static LinkedList<Integer> list[];

	// static Queue<Integer> q = new LinkedList<>();

	public static void main(String args[]) throws Exception
	{
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new LinkedList[N + 1];
		EdgeIn = new int[N + 1];

		for (int i = 0 ; i < M ; ++i){
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			++EdgeIn[b];
			if (list[a] == null){
				list[a] = new LinkedList<Integer>();
			}
			list[a].add(b);
		}

		// for (int i = 1 ; i <= N ;++i){
		// 	nodes.add(new Node(i, EdgeIn[i]));
		// }

		// while(!nodes.isEmpty()){
		// 	//항상 문제를 모두 풀 수 있는 경우만 입력으로 주어지므로, nodes pq의 top이 항상 0인것이 보장이 된다.
		// 	Node curr = nodes.poll();

			
		// }

		int count = 0;
		boolean visited[] = new boolean[N + 1];

		while (count < N){
			for (int i = 1; i < N + 1; ++i){
				if (EdgeIn[i] == 0 && visited[i] == false){
					++count;
					sb.append(i).append(" ");
					visited[i] = true;
					if (list[i] == null)
						continue;
					Iterator it = list[i].iterator();

					while(it.hasNext()){
						--EdgeIn[(int) it.next()];
					}
					i = 0; //가능하면 쉬운 문제를 풀어야하므로 순회를 앞으로 다시 돌린다.
					// while(!q.isEmpty()){
					// 	--EdgeIn[q.poll()];
					// }//이번에 삭제된 엣지들 삭제
				}
			}//end of for i
		}//end of while
		System.out.println(sb.toString());
	}//end of main method
}//end of class
/*
 
1 ~N 번까지 N개 문제로 되어있는 문제집,
난이도 순서로 출제. -> N번이 가장 어려운 문제


N개의 문제를 모두 풀어야한다.
선수 문제 먼저 풀어야한다.
가능하면 쉬운 문제 부터 풀어야한다.

민오가 풀 문제의 순서를 결정해주는 프로그램을 작성

1 <= N <= 32,000

- 접근 방식
위상정렬 순으로 q에 넣지만, 
in이 0인 문제를 넣을 때, 문제 번호 작은 순으로 넣는다.
--> 앞에서부터 순회하는 위상정렬로 풀면 될거 같은데?

 */