import java.util.*;
import java.io.*;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N;
	static Stack<Integer> st = new Stack<>();
	static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) throws IOException{
		N = sc.nextInt();
		for(int i = 0; i < N; ++i){
			String order = sc.next();
			if(order.equals("push")){
				st.push(sc.nextInt());
			}
			else if (order.equals("top")){
				if(st.isEmpty())
					sb.append("-1").append('\n');
				else
					sb.append(st.peek()).append('\n');
			}
			else if (order.equals("size")){
				sb.append(st.size()).append('\n');
			}
			else if (order.equals("empty")){
				sb.append(st.isEmpty()? 1 : 0).append('\n');
			}
			else if (order.equals("pop")){
				if(st.isEmpty())
					sb.append("-1").append('\n');
				else
					sb.append(st.pop()).append('\n');
			}
		}
		System.out.print(sb);
	}//end of main method


}//end of main class
