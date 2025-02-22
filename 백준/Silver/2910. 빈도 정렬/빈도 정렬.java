import java.util.*;
import java.io.*;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N;
	static int C;

	static Map<Integer, Integer> countMap = new HashMap<>();
	static Map<Integer, Integer> indexMap = new HashMap<>();

	static StringBuffer sb = new StringBuffer();

	static class Info{
		int num;
		int count;
		int index;

		Info(int n , int c, int i){
			num = n;
			count = c;
			index = i;
		}
	}

	static PriorityQueue<Info> pq = new PriorityQueue<>((o1,o2)-> {
		if (o1.count == o2.count)
			return o1.index - o2.index;
		return o2.count - o1.count;
	});

	public static void main(String[] args) throws IOException{
	
		N = sc.nextInt();
		C = sc.nextInt();

		for(int i = 0; i < N; ++i){
			int curr = sc.nextInt();

			if (countMap.containsKey(curr)){
				countMap.replace(curr, countMap.get(curr) + 1);
				continue;
			}
			else{
				countMap.put(curr, 1);
				indexMap.put(curr, i);
			}
		}

		for (Map.Entry<Integer, Integer> cm : countMap.entrySet()){
			pq.add(new Info(cm.getKey(), cm.getValue(), indexMap.get(cm.getKey())));
		}

		while(!pq.isEmpty()){
			Info curr = pq.poll();
			for(int i = curr.count; i > 0; --i){
				sb.append(curr.num).append(' ');
			}	
		}

		System.out.println(sb.toString());
	 
	}//end of Main
}