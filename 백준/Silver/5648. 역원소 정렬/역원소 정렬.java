import java.util.*;
import java.io.*;


public class Main {
	static ArrayList<String> arr = new ArrayList<>();
	static int N;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Scanner sc = new Scanner(System.in);
	static StringBuffer sb = new StringBuffer();
	public static void main(String[] args) throws IOException{
		// st = new StringTokenizer(br.readLine());
		// N = Integer.parseInt(st.nextToken());
		N = sc.nextInt();
		for(int i = 0; i < N; ++i){
			String curr = sc.next();
			arr.add(reverseElement(curr));
		}
		// Collections.reverse(arr);
		arr.sort((o1,o2)-> {
			if(o1.length() == o2.length())
				return o1.compareTo(o2);
			return o1.length() - o2.length();
		});
		
		for (String s : arr){
			 sb.append(s).append('\n');
		}
		System.out.print(sb.toString());
	}//end of main

	static String reverseElement(String s){
		StringBuffer ans = new StringBuffer();
		boolean flag = false;
		int length = s.length();
		for(int i = length - 1; i >= 0; --i){
			char curr = s.charAt(i);
			if (!flag && curr == '0')
				continue;
			if (!flag && curr != '0')
				flag = true;
			ans.append(curr);
		}
		if (ans.length() == 0)
			ans.append('0');
		return ans.toString();
	}

}//end of Main
