import java.util.*;
import java.io.*;

public class Main {
	static long[] burger = new long[51];
	static long[] pattie = new long[51];
	static int N;
	static long X;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		burger[0] = 1;
		pattie[0] = 1;
		
		N = sc.nextInt();
		X = sc.nextLong();
		
		for (int i = 1 ; i < N; ++i) {
			burger[i] = 2 * burger[i - 1] + 3;
			pattie[i] = 2 * pattie[i - 1] + 1;
		}
		
		long answer = getPattie(N, X);
		System.out.println(answer);
	}//end of main
	
	static long getPattie(int level, long X) {
		if (level == 0)
			return 1;
		if (X == 1) //맨 아래 빵만 먹은 경우
			return 0;
		if (X == burger[level - 1] + 2) //정 중앙까지 먹었다면
			return pattie[level - 1] + 1;
		if (X == burger[level - 1] + 1) //level - 1 버거까지 먹었다면
			return pattie[level - 1];
		if (X == burger[level] - 1 || X == burger[level]) // 번만 빼고 다 먹었거나 모든 층을 전부 다 먹었다면
			return pattie[level];
		if (X > burger[level - 1] + 2)
			return pattie[level - 1] + 1 + getPattie(level - 1, X - (burger[level - 1] + 2));
		else
			return getPattie(level - 1, X - 1);
	}//end of getPattie
	
}//end of class
