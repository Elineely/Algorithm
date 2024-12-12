import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Monkey{
	int y;
	int x;
	int k;
	int count;

	Monkey(int y, int x, int k, int count){
		this.y = y;
		this.x = x;
		this.k = k;
		this.count = count;
	}
}

public class Main {
	static int minCount = Integer.MAX_VALUE;
	static int W;
	static int H;
	static int maxK;
	
	//말처럼 움직이기 델타 배열
	static int[] hy = {1, 1, 2, 2, -1, -1, -2, -2};
	static int[] hx = {-2, 2, 1, -1, -2, 2, 1, -1};
	
	//한칸씩 움직이기
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	static int[][] map;
	
	static boolean[][][] visited;
	
	static PriorityQueue<Monkey> pq = new PriorityQueue<Monkey>(new Comparator<Monkey>() {
			@Override
			public int compare(Monkey o1, Monkey o2) {
				return (o1.count - o2.count > 0 ? 1 : -1);
			}
	});

	static void moveMonkey() {

		while(!pq.isEmpty()) {
			Monkey current = pq.poll();
			
			int y = current.y;
			int x = current.x;
			int K = current.k;
			int count = current.count;
	
			if (y == H - 1 && x == W - 1) {
				minCount = count;
				return;
			}
			if (K > 0) {
				for (int i = 0; i < 8; ++i) {
					int nextY = y + hy[i];
					int nextX = x + hx[i];
					if (nextY < 0 || nextX < 0 || nextY >= H || nextX >= W || 
						map[nextY][nextX] == 1  || visited[nextY][nextX][K - 1]) 
						continue ;
					visited[nextY][nextX][K - 1] = true;
					pq.offer(new Monkey(nextY, nextX, K - 1, count + 1) );
				}
			}
			for (int i = 0; i < 4; ++i) {
				int nextY = y + dy[i];
				int nextX = x + dx[i];
				if (nextY < 0 || nextX < 0 || nextY >= H || nextX >= W || 
					map[nextY][nextX] == 1  || visited[nextY][nextX][K]) {
					continue ;
				}
				visited[nextY][nextX][K] = true;
				pq.offer(new Monkey(nextY, nextX, K, count + 1));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		maxK = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		//맵 입력 받음
		map = new int[H][W];
		for (int i = 0; i < H; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int k = 0; k < W; ++k) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[H][W][maxK + 1];
		visited[0][0][maxK] = true;
		pq.offer(new Monkey(0, 0, maxK, 0));
		moveMonkey();
		System.out.println(minCount == Integer.MAX_VALUE ? -1 : minCount);
	}
}//end of class
