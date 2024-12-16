import java.util.*;
import java.io.*;

class Main
{
	static int N;
	static int M;
	static int H;
	
	static int answer = Integer.MAX_VALUE;

	static int[][] map;

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();

		//이미 i번째가 i로 간다면 -> 0출력
		if(M == 0){
			System.out.println("0");
			return;	
		}

		map = new int[H+1][N+1];

		//사다리 입력 받기
		for(int i = 0 ; i < M ; ++i){
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = b + 1;
			map[a][b + 1] = b;
		}//사다리가 있는 곳에는 0이 아닌 값이 존재하며, 
		//해당 사다리를 만날 경우 이동할 x 값을 가지고 있다.

		//사다리를 설치하지 않아도 값이 나오는 경우
		if(isAnswer()){
			System.out.println("0");
			return;
		}
		// printMap();

		//규칙에 맞는 사다리를 선택하는 dfs
		for (int i = 1 ; i <= H; ++i){
			for (int k = 1; k < N; ++k){ //사다리는 항상 오른쪽 방향으로 설치한다고 가정
				//인접한 사다리를 놓게되는 경우 제외
				// if (k >= 2 && map[i][k - 1] != 0)
				// 	continue;
				if (map[i][k + 1] != 0 || map[i][k] != 0) //이미 사다리가 있는 경우 포함
					continue;
				map[i][k] = k + 1;
				map[i][k + 1] = k;
				dfs(i, k, 1);
				map[i][k] = 0;
				map[i][k + 1] = 0;
			}
		}
		if(answer == Integer.MAX_VALUE)
			answer = -1;
		System.out.println(answer);

	}//end of main

	static void dfs(int y, int x, int count){
		if (count > 3) //정답 없음
			return;
		if (isAnswer()){
			if(answer > count){
				answer = count;
			}
			return;
		}	
		for (int i = y; i <= H; ++i){
			for (int k = 1; k < N ; ++k){ //사다리는 항상 오른쪽 방향으로 설치한다고 가정
				//인접한 사다리를 놓게되는 경우 제외
				//if (k >= 1 && map[i][k - 1] != 0)
				  //  continue;
				if (map[i][k] != 0 ||  map[i][k + 1] != 0)
					continue;
				map[i][k] = k + 1;
				map[i][k + 1] = k;
				dfs(i, k, count + 1);
				map[i][k] = 0;
				map[i][k + 1] = 0;
			}
		}

	}

	static boolean isAnswer(){
		//사다리를 1번 부터 차례대로 타는 코드
		//1개라도 자신의 번호에 닿지 않으면 false 반환
		for (int i = 0 ; i < N; ++i){
			int x = i;
			for(int k = 0; k < H + 1; ++k){
				if (map[k][x] != 0)
					x = map[k][x];
			}
			if(x != i){
				return false;
			}
		}
		return true;
	}

	static void printMap(){
		for(int i = 1; i < H + 1; ++i){
			for(int k = 1; k < N + 1 ; ++k){
				System.out.print(map[i][k] + " ");
			}
			System.out.println();
		}
	}


}//end of solution

/*
 * 20억 연산
 * 2 <= N <= 10 --> 세로선 갯수
 * 1 <= H <= 30 ==> 가로선 가능한 높이
 * 0 <= M <= (N - 1) x H --> 최대 270 ==> 현재 가로선의 갯수
 * 
 */