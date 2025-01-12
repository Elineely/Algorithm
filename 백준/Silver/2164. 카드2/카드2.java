import java.util.*;
import java.io.*;

class Main
{
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i = 1; i <= N; ++i){
			q.add(i);
		}
		
		while(q.size() > 1){
			q.poll();
			q.add(q.poll());
		}

		System.out.println(q.poll());
	}//end of main
}//end of class
