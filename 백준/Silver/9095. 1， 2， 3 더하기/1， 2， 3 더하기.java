import java.util.*;
import java.io.*;


public class Main {
	
	static int[] arr = new int[11];
	
	public static void main(String[] args) throws IOException{
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;

		for(int i = 4; i < 11; ++i){
			arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
		}

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i = 0; i < T; ++i){
			int num = sc.nextInt();
			System.out.println(arr[num]);
		}
	}//end of main

}//end of Main