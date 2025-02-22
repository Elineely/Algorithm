import java.util.*;
import java.io.*;


public class Main {
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;

	static StringBuffer sb = new StringBuffer();
	static PriorityQueue<String> pq = new PriorityQueue<>((o1,o2) -> {
		if(o1.length() == o2.length())
			return o1.compareTo(o2);
		return o1.length() - o2.length();
	});
	
	static Set<String> s = new HashSet<>();
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		for(int i = 0 ; i < N ; ++ i){
			st = new StringTokenizer(br.readLine());
			String curr = st.nextToken();
			if (s.contains(curr))
				continue;
			s.add(curr);
			pq.add(curr);
		}

		while(!pq.isEmpty()){
			sb.append(pq.poll()).append('\n');
		}
		System.out.print(sb.toString());
	}//end of main
}//end of Main
