import java.util.*;
import java.io.*;


public class Main {
	static int M;
	static int N;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int count = 0; 
	static Set<Integer> s = new HashSet<>();
	static int[] origin;
	static ArrayList<Integer> rank;
	static Map<String, Integer> universe = new HashMap<>();
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
	
		for(int i = 0 ; i < M; ++i){
			origin = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < N; ++k){
				origin[k] = Integer.parseInt(st.nextToken());
				s.add(origin[k]);
			}
			rank = new ArrayList<>(s);
			Collections.sort(rank);
			StringBuffer curr = new StringBuffer();
			for (int l = 0; l < N; ++l){
				curr.append(Collections.binarySearch( rank, origin[l]));
			}
			// if(universe.include(curr.toString()))
			if (universe.containsKey(curr.toString()))
				universe.replace(curr.toString(), universe.get(curr.toString()) + 1);
			else
				universe.put(curr.toString(), 1);
			s.clear();
		}

		for(Map.Entry<String,Integer> curr :  universe.entrySet()){
			if(curr.getValue() == 1)
				continue;
			int n = curr.getValue();
			count += n * ( n - 1) / 2;
		}

		System.out.println(count);
	}//end of main method

}//end of main class