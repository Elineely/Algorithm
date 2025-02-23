import java.util.*;
import java.io.*;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuffer sb = new StringBuffer();
	static int N;

	static int[] origin;
	static Set<Integer> s = new HashSet<>();
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		origin = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i){
			origin[i] = Integer.parseInt(st.nextToken());
			s.add(origin[i]);
		}

		ArrayList<Integer> rank = new ArrayList<>(s);
		Collections.sort(rank);
		for(int i = 0; i < N; ++i){
			sb.append(Collections.binarySearch(rank, origin[i])).append(' ');
		}
		System.out.println(sb);
	}//end of main method

}//end of main class