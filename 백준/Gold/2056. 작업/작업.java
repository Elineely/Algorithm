import java.util.*;
import java.io.*;
 
public class Main {
    static int N;
    static Scanner sc = new Scanner(System.in);
    static int[] time;
    static List<Integer>[] edge;
    static int[] prev;
    static Queue<Integer> q = new LinkedList<>();
    static Queue<Integer> tempQ = new LinkedList<>();
    // static 
    static int answer = 0;

    public static void main(String[] args) {
        N = sc.nextInt();
        time = new int[N + 1];
        edge = new List[N + 1];
        prev = new int[N + 1];
        for(int i = 1; i <= N; ++i){
            edge[i] = new ArrayList<>();
        }
        int[] dp = new int[N + 1];

        for (int i = 1 ; i <= N; ++i){
            time[i] = sc.nextInt();
            int count = sc.nextInt();
            prev[i] = count;
            if(count == 0){
                q.add(i);
                dp[i] = time[i];
            }
            for (int k = 0; k < count; ++k) {
                int temp = sc.nextInt();
                edge[temp].add(i);
            }
        }

        while(!q.isEmpty()){
            int curr = q.poll();
            for(Integer next : edge[curr]){
                dp[next] = Math.max(dp[next], dp[curr] + time[next]);
                --prev[next];
                if(prev[next] == 0)
                    q.add(next);
            }
        }

        for(Integer value : dp){
          answer = Math.max(answer, value);
        }

        System.out.println(answer);
    } //end of main method
}//end of Main class

/*
 * 모든 작업을 완료하기 위한
 * 최소 시간 출력
 * 11:03~
 * 
 * 선행 작업갯수 배열
 * 엣지 정보
 */