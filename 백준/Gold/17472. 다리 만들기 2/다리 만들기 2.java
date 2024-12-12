import java.util.*;
import java.io.*;

public class Main {
	static class Bridge {
		int cost;
		int src;
		int dest;
		int y;
		int x;
		
		Bridge(int c, int s, int d, int y, int x){
			this.cost = c;
			this.src = s;
			this.dest = d;
			this.y = y;
			this.x = x;
			
		}
	}
	
	static PriorityQueue<Bridge> pq = new PriorityQueue<>((o1, o2) -> {return o1.cost - o2.cost;});
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static boolean bVisited[][][];
	
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	
	static Queue<Point> q = new LinkedList<>();
	static PriorityQueue<Bridge> bq = new PriorityQueue<>((o1, o2) -> {return o2.cost - o1.cost;}); //최대힙
	
	static int N;
	static int M;
	
	static int answer = -1;
	
	static int map[][];
	static boolean visited[][];
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		bVisited = new boolean[N][M][2]; //0 가로 //1 세로
		
		for (int i = 0 ; i < N; ++i){
			st = new StringTokenizer(br.readLine(), " ");
			for (int k = 0 ; k < M; ++k) {
				map[i][k] = Integer.parseInt(st.nextToken()); 
			}//end of for k
		}//end of for i
		
		//번호 메기기
		int num = 2;
		for(int i = 0 ; i < N; ++i) {
			for(int k = 0 ; k < M; ++k) {
				if (map[i][k] == 1) {
					paint(i, k, num);
					num++;
				}
			}//end of k
		}//end of i
		
		//다리 도출
		for (int i = 0; i <N; ++i) {
			for (int k = 0; k < M; ++k){
				if (map[i][k] > 1) {
					for(int idx = 0; idx < 4; ++idx) {
						getBridge(i, k, idx);
					}
				}
			}
		}

        //MST(최소신장트리) 구하기
		int candi = 0;
		int countBridge = 0;
		for (int i = 1 ; i < 8; ++i ) {
			parents[i] = i;
		}
		while(!pq.isEmpty()) {
			Bridge curr = pq.poll();
			if (parents[curr.src] == parents[curr.dest])
				continue;
			candi += curr.cost;
			countBridge++;
			int target = Math.max(parents[curr.src], parents[curr.dest]);
			int to = Math.min(parents[curr.src], parents[curr.dest]);
			for(int i = 0 ; i < 8; ++i) {
				if(parents[i] == target)
					parents[i] = to;
			}
		}
		
		//답 출력
		if(countBridge == (num - 2) - 1)
			answer = candi;
		else 
			answer = -1;
		
		System.out.println(answer);
	}//end of main
	
	static int[] parents = new int[8];

	static void getBridge(int y, int x, int idx) {
		int num = map[y][x];
		int dir = (idx < 2) ? 0 : 1;
		
		Bridge curr = new Bridge(0, num, 0, y, x);
		bq.add(curr);
		while (!bq.isEmpty()) {
			curr = bq.poll();
			int nY = curr.y + dy[idx];
			int nX = curr.x + dx[idx];	

			if (nY < 0 || nX < 0 || nY >= N || nX >= M || bVisited[nY][nX][dir])
				continue;
			bVisited[nY][nX][dir] = true;
			if (map[nY][nX] == 0)
				curr.cost++;
			if(curr.cost > 0  && map[nY][nX] == num)
				break;
			if (map[nY][nX] > 0 && map[nY][nX] != num) {
				if (curr.cost >= 2)
					pq.add(new Bridge(curr.cost, num, map[nY][nX], nY, nX));
				break;
			}
			else {
				bq.add(new Bridge(curr.cost, num, 0, nY, nX));
			}
		}
	}
	

	static void paint(int y , int x, int num) {
		q.add(new Point(y,x));
		visited[y][x] = true;
		map[y][x] = num;
		while (!q.isEmpty()) {
			Point curr = q.poll();
			for (int i = 0 ; i < 4; ++i) {
				int nY = curr.y + dy[i];
				int nX = curr.x + dx[i];
				
				if(nY < 0 || nY >= N || nX < 0 || nX >= M || map[nY][nX] != 1 || visited[nY][nX])
					continue;
				map[nY][nX] = num;
				q.add(new Point(nY, nX));
				visited[nY][nX] = true;
			}
		}//end of while
		q.clear();
	}
	
	static void printMap() {
		for(int i = 0 ; i < N; ++i) {
			for(int k = 0; k < M; ++k) {
				System.out.print(map[i][k]);
			}
		System.out.println();
		}
	}
	
	static void printBV() {
		System.out.println("===================");
		for(int i = 0 ; i < N; ++i) {
			for(int k = 0; k < M; ++k) {
				System.out.print(bVisited[i][k][0]);
			}
		System.out.println();
		}
		System.out.println("===================");
		for(int i = 0 ; i < N; ++i) {
			for(int k = 0; k < M; ++k) {
				System.out.print(bVisited[i][k][0]);
			}
		System.out.println();
		}
	}
	

	static void printPQ() {
		while(!pq.isEmpty()) {
			Bridge curr = pq.poll();
			System.out.println(curr.cost + " | " + curr.src + " -> " + curr.dest);
		}
	}
	
	
	static class Point{
		int y;
		int x;
		
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}//end of Main class
