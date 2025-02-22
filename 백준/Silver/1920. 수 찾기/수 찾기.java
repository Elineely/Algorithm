import java.util.*;
import java.io.*;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuffer sb = new StringBuffer();
	static ArrayList<Integer> A = new ArrayList<>();
	
	static int N;
	static int M;
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i){
			A.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(A);

		st = new StringTokenizer(br.readLine());
	 	M = Integer.parseInt(st.nextToken());
	
		 st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i){
			int target = Integer.parseInt(st.nextToken());

			boolean isExist = findInA(target);
			if(isExist)
				sb.append('1');
			else
				sb.append('0');
			sb.append('\n');
		}
	
		System.out.print(sb);
	}//end of main method

	static boolean findInA(int target){
		int left = 0;
		int right = N - 1;
		int mid;
		while(true){			
			if (left > right)
				break;
			mid = (left + right) / 2; 
			// System.out.println("l : " + left + " , r : " + right + " , mid : "+ mid);
			if(A.get(mid) == target)
				return true;
			if (A.get(mid) > target)
				right = mid - 1;
			else
				left = mid + 1;
		}
		return false;
	}

}//end of main class