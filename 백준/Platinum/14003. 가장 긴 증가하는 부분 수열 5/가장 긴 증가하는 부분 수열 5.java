import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//입력 받아 저장
		int N = sc.nextInt();
		int arr[] = new int[N];

		for(int i = 0; i < N; ++i) {
			arr[i] = sc.nextInt();
		}
		
		List<Integer> list = new ArrayList<>();
		
		list.add(arr[0]);
		
		int index[] = new int[N];
		
		
		for(int i = 1; i < N; ++i) {
			int curr = arr[i];
			if(curr > list.get(list.size() - 1)) {
				index[i] = list.size();
				list.add(curr);
			}else {
				//갱신이 필요한 부분 -> 이분탐색으로 찾기
				
				int left = 0;
				int right = list.size();
				int t = 0;
				while(left < right) {
					int mid = left + (right - left) / 2;
					
					if (list.get(mid) >= curr) {
						right = mid;
					} else {
						t = mid + 1;
						left = mid + 1; 
					}
				}
//				System.out.println(curr + " " + t);
				list.set(t, curr);
				index[i] = t;
			}
		}
		System.out.println(list.size());
		
		Stack<Integer> s = new Stack<>();
		int lastIndex = list.size() - 1;
		
		for(int i = N - 1; lastIndex >= 0 && i >= 0; --i) {
			if (index[i] != lastIndex)
				continue ;
			lastIndex--;
			s.push(arr[i]);
		}
		
		while(!s.isEmpty()) {
			System.out.print(s.pop() + " ");
		}
		
		//가장 긴 증가부분 수열 찾는 List 만들기
		//도중에 인덱스도 함께 저장
		//가장긴 증가수열의 길이는 list의 길이
		
		
		//뒤에서부터 list 뒷 인덱스부터 스택에 저장
		
		
		//스택 출력
		
	}//end of main
}//end of Class
