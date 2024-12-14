import java.io.*;
import java.util.*;

public class Main {
	static int[] quantity = {0, 5, 5, 5, 5, 5};
	static boolean [][] visited  = new boolean[10][10];
	static int[][] map = new int[10][10];
	static int minCount = 26;
	
	private static void removeOne(int y, int x, int count, int numOfOne) {
		//더이상 0이 없으면
		if (count > minCount) {
			return ;
		}
		if (numOfOne == 0) {
			minCount = count;
			return ;
		}

		//이번에 방문해야할 좌표 찾기
		findNext : for (int i = y; i < 10; ++i){
			for (int k = 0; k < 10; ++k) {
				if (map[i][k] == 1 && !visited[i][k]) {
					y = i;
					x = k;
					break findNext;
				}
			}
		}
		int size = 5;
		for (;size > 0; --size) {
			if (quantity[size] < 1) {
				continue;
			}
			if (!visited[y][x] && getPatched(size, y, x) ){
				//방문처리
				makePatch(size, y, x);
				--quantity[size];

				removeOne(y, x, count + 1, numOfOne - size * size);
				
				//방문처리 해제
				++quantity[size];
				removedPatch(size, y, x);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int numOfOne = 0;

		//입력 받기
		for (int i = 0; i < 10; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int k = 0; k <10; ++k) {
				int current = Integer.parseInt(st.nextToken());
				map[i][k] = current;
				if (current == 1) {
					++numOfOne;
				}
			}
		}
		
		if (numOfOne == 0) {
			System.out.println(0);
			return ;
		}
		
		//1 부분 없애기
		find : for (int i = 0; i <10; ++i) {
			for (int k = 0; k <10; ++k) {
				if (map[i][k] == 1) {
					removeOne(i, k, 0, numOfOne);
					break find;
				}
			}//END OF FOR K
		}//END OF FOR I

		System.out.println(minCount == 26? -1 : minCount);
	}//end of main
	
	static void removedPatch(int n, int y, int x){
		for(int i = y; i < y + n; ++i) {
			for( int k = x; k < x + n; ++k) {
				visited[i][k] = false;
			}
		}
	}
	static void makePatch(int n, int y, int x){
		for(int i = y; i < y + n; ++i) {
			for( int k = x; k < x + n; ++k) {
				visited[i][k] = true;
			}
		}
	}
	
	static boolean getPatched(int n, int y, int x){
		for (int i = y; i < y + n; ++i) {
			for(int k = x; k < x + n; ++k) {
				if (i >= 10 || k >= 10 || map[i][k] == 0 || visited[i][k]) {
					return false;
				}
			}
		}
		return true;
	}

}//end of class
