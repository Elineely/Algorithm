import java.util.*;
import java.io.*;


public class Main {
	static int N ;
	static class Card{
		int count;
		long num;
	}

	static Map<Long, Integer> map = new HashMap<>();

	static PriorityQueue<Card> pq = new PriorityQueue<>((o1,o2)-> {
		if(o1.count == o2.count)
			return o1.num - o2.num < 0 ? -1 : 1;
		return o2.count - o1.count;
	});

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException{
		N = sc.nextInt();
		
		for(int i = 0 ; i < N; ++i){
			long curr = sc.nextLong();
			if (map.containsKey(curr)){
				map.replace(curr, map.get(curr) + 1);
			}
			else{
				map.put(curr, 1);
			}
		}

		long ansNum = Long.MIN_VALUE;
		int ansCount = 0;
		for(long key : map.keySet()){
			int currCount = map.get(key);
			if(currCount < ansCount)
				continue;
			if(currCount == ansCount){
				if(ansNum > key)
					ansNum = key;
			}
			else{
				ansNum = key;
				ansCount = currCount;
			}
		}
		System.out.println(ansNum);
	}//end of main

}//end of Main
