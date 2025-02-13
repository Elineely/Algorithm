import java.util.*;
import java.io.*;


public class Main {
	
	// static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// static StringTokenizer st;
	
	static int N;
	static int K;

	static int[] count = new int[100001]; //몇개까지 만들수 있나?
	static int[] arr = new int[200001];
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException{
	
		// st = new StringTokenizer(br.readLine());
		N = sc.nextInt();
		K = sc.nextInt();

		// st = new StringTokenizer(br.readLine());
		int start = 0;
		int max = 0;
		int length = 0;
		for (int i = 0; i < N; ++i){
			arr[i] = sc.nextInt();
		}
		for (int i = 0; i < N; ++i){
			// int curr = arr[i];
			// System.out.println(i + "   " +  curr + " " + start + " " + length );
			count[arr[i]]++;
			++length;
			while (count[arr[i]] > K){
				count[arr[start]]--;
				start++;
				--length;
				if (start == i)
					break;
			}
			if (length > max)
				max =length;
		}
		System.out.println(max);
	}//end of main

}
/*
 * 같은 원소 K개 이하 들어있는
 * 최장 연속 부분 수열의 길이
 * 
 * 슬라이딩 윈도우로 풀수 있지 않을까?
 * -> 최대 20만
 */