import java.util.*;
import java.io.*;


public class Main {

	 static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 static StringTokenizer st;
	 static StringBuffer sb = new StringBuffer();

	 static int N;

	static class Serial{
		int length;
		int sum;
		String origin;

		Serial(int l, int s, String o){
			length = l;
			sum = s;
			origin = o;
		}
	}

	static PriorityQueue<Serial> pq = new PriorityQueue<>((o1, o2)->{
		if (o1.length == o2.length){
			if(o1.sum == o2.sum){
				return o1.origin.compareTo(o2.origin);
			}
			return o1.sum - o2.sum;
		}
		return o1.length - o2.length;
	});

	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; ++i){
			st = new StringTokenizer(br.readLine());
			String curr = st.nextToken();
			pq.add(new Serial(curr.length(), getSum(curr), curr));
		}

		while(!pq.isEmpty()){
			Serial curr = pq.poll();
			sb.append(curr.origin).append('\n');
		}
		System.out.print(sb);
	}//end of Main


	static int getSum(String str){
		int l = str.length();
		int sum = 0;
		for(int i = 0 ; i < l; ++i){
			if (str.charAt(i) < 'A'){
				sum += str.charAt(i) - '0';
			}
		}
		return sum;
	}
}