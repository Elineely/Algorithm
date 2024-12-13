import java.util.*;
import java.io.*;

class Main
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static class Area{
		int minY;
		int minX;

		int totalCount;
		int zeroCount;

			Area(int mY, int mX, int tC, int zC){

			minY = mY;
			minX = mX;

			totalCount = tC;
			zeroCount = zC;
		}
	}

	static PriorityQueue<Area> pqArea = new PriorityQueue<>((o1, o2)->{
		if(o1.totalCount == o2.totalCount){
			if(o1.zeroCount == o2.zeroCount){
				if (o1.minY == o2.minY){
					return o2.minX - o1.minX;
				}
				return  o2.minY - o1.minY;
			}
			return o2.zeroCount - o1.zeroCount;
		}
		return o2.totalCount - o1.totalCount;
	});

	static int N;
	static int M;
	static int[][] map;
	static int[] blockCount;

	static int score;

	static int dy[] = {0, 0, 1, -1};
	static int dx[] = {1, -1, 0, 0};

	public static void main(String args[]) throws Exception
	{
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		blockCount = new int[M + 1]; // 1~M

		for (int i = 0 ; i < N ; ++i){
			st = new StringTokenizer(br.readLine(), " ");
			for(int k = 0 ; k < N ; ++k){
				map[i][k] = Integer.parseInt(st.nextToken());
				if (map[i][k] > 0){
					++blockCount[map[i][k]];
				}
			}
		}//map 입력 받기


		while (true){ 
			//가장 큰 블록 판별하기 
			findBiggest();
			if (pqArea.isEmpty())
				break; //더이상 영역을 만들 수 없을 때 while 탈출
			
            //가장 큰 블록 지우기 + 점수 획득
			removeBiggest();
			
			//중력 작용(-1블록은 제외)
			doGravity();

			//90도 반시계 회전
			doRotate();

			//중력 작용(-1블록은 제외)
			doGravity();

			pqArea.clear();
		//오토플레이가 끝났을 때 획득한 점수의 합을 구한다.
		}
		System.out.println(score);

	} // end of main method

	static void doRotate(){
		int[][] temp = new int[N][N];

		for(int i = 0; i < N; ++i){
			for(int k = 0 ; k < N; ++k){
				temp[i][k] = map[k][N - i - 1] ;
			}
		}
		map = temp;
	}

	static void doGravity(){
		//가장 밑(bottom)에서 부터 빈공간 탐색(-2)
		
		for(int k = 0; k < N; ++k){
			for(int i  = N - 1; i > 0; --i){ //빈공간 위치 찾아서 빈 공간이 맨 위층에 있으면 의미가 없음.
				int bottom;
				int target = -1;
				int targetCount = 0;
				if (map[i][k] == -2){
					bottom = i;

					while (i >= 0 && map[i][k] == -2){
						--i;	
					}//빈공간이나 검은블록 아닌 곳 까지 y가 올라간다(작아진다).
					
					if (i < 0)
						continue;// 낙하할 블록이 없는 경우

					if (map[i][k] >= 0)
						target = i; // 낙하할 블록이 있는 경우

					//검은 블록을 만나는 경우  target 정하지 않고 무시
					if (target == -1  || (i >= 0 && map[i][k] == -1))
						continue;


					while (i >= 0 && map[i][k] >= 0){
						++targetCount;
						--i;
					}
					//블록 낙하를 위한 값 복사
					for (int a = 0; a < targetCount; ++a){
						map[bottom - a][k] = map[target - a][k];
						map[target - a][k] = -2;//빈공간
					}
					i = N;
				}
			}
		}
	}



	static class Point{
		int y ;
		int x;

		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}


	static void bfs(int y, int x, boolean[][][] visited){
		Queue<Point> q = new LinkedList<>();
		
		int standard = map[y][x];
		int totalCount = 1;
		int zeroCount = 0;
		int normalCount = 1;

		int mY = y;
		int mX = x;
		visited[y][x][standard] = true;
		q.add(new Point(y, x));
		while(!q.isEmpty()){
			Point curr = q.poll();
			for (int i = 0 ; i < 4; ++i){
				int nY = curr.y + dy[i];
				int nX = curr.x + dx[i];
				if ( nY < 0 || nX < 0 || nY >= N || nX >= N ||
					map[nY][nX] < 0 || 
					(map[nY][nX] != 0 && map[nY][nX] != standard)
					|| visited[nY][nX][standard]) //선택할 수 없는 블록 조건들
					continue;
				
				q.add(new Point(nY, nX));
				visited[nY][nX][standard] = true;
				if (map[nY][nX] == 0)
					++zeroCount;
				else if(map[nY][nX] > 0)
					++normalCount;
				++totalCount;
				if(map[nY][nX] == 0)
					continue;
				//무지개 블록이 아닌 블록중에서 기준 블록 위치 갱신
				if (nY == mY && nX < mX) mX = nX;
				if (nY < mY) {mY = nY; mX = nX;}
			}
		}//end of while

		//영역을 만들수 없는 조건들이라면 
		if (normalCount < 1)
			return;
		if (totalCount < 2)
			return;

		pqArea.add(new Area(mY, mX, totalCount, zeroCount));
	}

	static void printMap(){
		System.out.println("===================================");
		for(int i = 0 ; i < N ; ++i){
			for(int k  = 0 ; k < N ; ++k){
				if(map[i][k] == -2)
					System.out.print("  ");
				else if(map[i][k] == -1)
					System.out.print("@ ");
				else
					System.out.print(map[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println("===================================");

	}


	static void removeBiggest(){
		//pq 내 가장 첫번째 원소에 있는 모든 블록 삭제(동시에 블록 카운트 삭제) 
		//점수 획득
		//pq 초기화
		Area target = pqArea.poll();

		Queue<Point> q = new LinkedList<>();

		int y = target.minY;
		int x = target.minX;
		int standard = map[y][x];
		
		--blockCount[standard];
		map[y][x] = -2;
		score += Math.pow( target.totalCount , 2);
		
		q.add(new Point(y, x));
		while(!q.isEmpty()){
			Point curr = q.poll();
			for (int i = 0 ; i < 4; ++i){
				int nY = curr.y + dy[i];
				int nX = curr.x + dx[i];
				if ( nY < 0 || nX < 0 || nY >= N || nX >= N ||
					map[nY][nX] < 0 // -1(검은블록)or -2(이미 삭제된 영역)
					|| 
					(map[nY][nX] != 0 && map[nY][nX] != standard) //이번 영역의 M이 아니면 다른 영역
					)
					continue;
				map[nY][nX] = -2; //블록 지우기
				--blockCount[standard]; // 남은 갯수 빼기
				q.add(new Point(nY, nX));

			}
		}//end of while
	}

	static void findBiggest(){
		boolean visited[][][] = new boolean[N][N][M + 1];

		for (int i = 0; i < N; ++i){
			for(int k = 0 ; k < N; ++k){
				if (map[i][k] > 0){
					bfs(i, k, visited);
				}
			}
		}
		//가장 큰 영역은 pq 내의 가장 첫번째 원소
	}


}//end of solution

//없음 => -2 (void); --> -2 라면 중력의 영향을 받아야한다.


/*
 * 블록 - 검 , 무지개, 일반
 * 일반 -> M가지 색상(1~M까지 색상으로 표현)
 * 검 ->  -1
 * 무지개 -> 0
 * 
 * 절대거리 1을 만족하는 두칸은 인접한 칸.
 
	블록 그룹 -> 연결된 블록의 집합
	==> 일반 적어도 하나 있고, (모두 색이 같아야한다.) 
	검은색 없어야.(무지개 상관 없음)
	2개 이상의 블록 포함
	
	임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동 후 다른 모든 칸으로 이동할 수 있어야한다?
	--> 중복 없이라는 말이 없으니 그냥 연결되어있어야한다.

	기준 블록은 일반 블록 중 행의 번호가 가장 작은 블록. 
	그 블록이 여러개면 열의 번호가 가장 작은 블록(x 최소값 블록)


	오토플레이 기능

	1. 가장 큰 블록그룹을 찾는다
		- 우선순위
		1.크기가 가장 큰 블록그룹 찾기
		2.무지개 블록수가 가장 많은 블록 그룹
		3. 기준 블록의 행이 가장 큰 
		4. 열이 가장 큰 
		--> pq로 구현하는게 편하겠다.

	2. 찾은 블록 그룹 모든 블록 제거 --> B개 블록 제거시 -> B^2 점 획득

	3. 격자에 중력이 작용
	4. 격자가 90도 반시계 방향으로 회전한다
	5. 다시 격자에 중력이 작용한다.

**** 검은 블록(-1)에는 중력이 작용하지 않는다.


 * 
 * 1초
 * 1<= N <= 20
 * 1<= M <= 5
 * 
 */