import java.util.*;
import java.io.*;


public class Main {
	static class Student{
		String name;
		int korean;
		int english;
		int math;

		Student(String name, int k, int e, int m){
			this.name = name;
			this.korean = k;
			this.english = e;
			this.math = m;
		}
	}
	
	static PriorityQueue<Student> pq = new PriorityQueue<>((o1, o2) -> {
		if(o1.korean != o2.korean)
			return o2.korean - o1.korean;
		if(o2.english != o1.english)
			return o1.english - o2.english;
		if(o2.math != o1.math)
			return o2.math - o1.math;
		// (o2.name, o1.name) -> return compare To();
		// if(o2.name o1.name)
		return o1.name.compareTo(o2.name);
	});

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static StringBuffer sb = new StringBuffer();
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		for (int i = 0 ; i < N; ++i){
			st = new StringTokenizer(br.readLine());
			String n = st.nextToken();
			int k = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			pq.add(new Student(n, k, e, m));
		}

		while(!pq.isEmpty()){
			String name = pq.poll().name;
			// System.out.println(name);
			sb.append(name).append('\n');
		}
		System.out.print(sb.toString());
	}//end of main

}
