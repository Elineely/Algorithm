import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int y;
	int x;
	
	Point(int y, int x){
		this.y = y;
		this.x = x;
	}
}

public class Main {
	static int N;
	static int M;
	static Queue<Point[]> newWall = new LinkedList<>();
	static Point[] empty = new Point[64];
	static Point[] virus = new Point[10];
	static int virusCount = 0;
	static int emptyCount = 0;
	static Point[] current = new Point[3];
	static int[][] origin;
	static int[][] map;
	
	
	static int[] dy = { 1, -1, 0, 0};
	static int[] dx = { 0, 0, 1, -1};
	static int maxValue = 0;
	
	static void makeCombi(int prev, int count) {
		if (count == 3) {
			Point[] temp = new Point[3];
			System.arraycopy(current, 0, temp, 0, 3);
			newWall.add(temp);
			return;
		}
		for(int i = prev + 1; i < emptyCount; ++i) {
			current[count] = empty[i];
			makeCombi(i, count + 1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		origin = new int[N][M];
		map = new int[N][M];
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int k = 0 ; k < M; ++k) {
				origin[i][k] = Integer.parseInt(st.nextToken());
				if (origin[i][k] == 0)
					empty[emptyCount++] = new Point(i,k);
				else if (origin[i][k] == 2) 
					virus[virusCount++] = new Point(i,k);
			}
		}
		
		//벽 3개짜리 조합 구하기
		for (int i = 0; i < emptyCount; ++i) {
			current[0] = empty[i];
			makeCombi(i, 1);
		}
		
		//각 조합에 대하여 시뮬레이션
		while(!newWall.isEmpty()) {
			for(int i = 0; i <N; ++i) {
				System.arraycopy(origin[i], 0, map[i], 0, M);				
			}
			findMaxValue(newWall.poll());
		}
		System.out.println(maxValue);
		
	}//end of main
	
	static void findMaxValue(Point[] walls) {
		int zeroCount = 0;
		
		//벽세워
		for(int i = 0 ; i < 3; ++i) {
			map[walls[i].y][walls[i].x] = 1;
		}
		
		//바이러스 퍼뜨려
		for (int i = 0; i < virusCount; ++i) {
			int currentY = virus[i].y;
			int currentX = virus[i].x;
			
			Queue<Point> q = new LinkedList<>();
			q.add(new Point(currentY, currentX));
			while(!q.isEmpty()) {
				Point t = q.poll();
				for(int k = 0; k < 4; ++k) {
					int nextY = t.y + dy[k];
					int nextX = t.x + dx[k];
					if(nextY < 0 || nextY >= N || nextX < 0 || nextX >= M ||
							map[nextY][nextX] != 0)
						continue;
					map[nextY][nextX] = 2;
					q.add(new Point(nextY,nextX));
				}
			}
		}
		
		//0의 갯수
		for (int i = 0; i < emptyCount; ++i) {
			Point a = empty[i];
			if (map[a.y][a.x] == 0) {
				++zeroCount;
			}
		}
		
		if(maxValue < zeroCount)
			maxValue = zeroCount;
	}
	
}

/*
 * N * M  ( 3<= N,M <= 8)
 * 바이러스 -> 인접상하좌우 확산----> 2이상 10개 이하 
 * 벽의 개수 3개 -> 반드시 3개
 * 0 -> 빈칸
 * 1 -> 벽
 * 2 -> 바이러스
 * 
 * 안전영역 크기 최대값을 구해라
 * 
 * 
 * 
 * 전략
 * dfs 로 벽을 세울 좌표 3개 고르기
 * -> 모든 조합 큐에 넣는다.
 * 
 * 
 * 각 조합 마다 바이러스 확산 시뮬레이션
 * 동시에 안정영역 크기 최댓값 구한다.
 * 
 */