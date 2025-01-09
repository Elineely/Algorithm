import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[] lights;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		//입력
		int N = Integer.parseInt(st.nextToken());
		lights = new boolean[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int n = 1; n < N + 1; ++n) {
			lights[n] = (st.nextToken().equals("1"))? true : false;
		}
		
		//학생 입력 받으며 run
		int S = Integer.parseInt(br.readLine());
		for (int s = 0; s < S; ++s) {
			st = new StringTokenizer(br.readLine(), " ");
			if (st.nextToken().equals("1")) {
				int di = Integer.parseInt(st.nextToken());
				for (int i = di; i <= N; i += di) {
					lights[i] = !lights[i];
				}
			}//남
			else {
				int mid = Integer.parseInt(st.nextToken());
				lights[mid] = !lights[mid];
				for (int i = 1; mid - i > 0 && mid + i <= N; ++i) {
					if (lights[mid - i] != lights[mid + i])
						break;
					boolean now = !lights[mid - i];
					lights[mid -i] = now;
					lights[mid + i] = now;
				}
			}//여
		}//end of student
		//20줄씩 출력
		for (int i = 0 ; i <= N / 20; ++i) {
			for (int k = 20 * i + 1; k <= 20 * i + 20 && k <= N; ++k) {
				sb.append(lights[k] == true? 1 : 0).append(" ");
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}//end of main
}
