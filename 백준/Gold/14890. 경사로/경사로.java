import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int count = 0;
        // 각 행을 검사
        for (int i = 0; i < N; i++){
            if (isValid(map[i], L))
                count++;
        }
        
        // 각 열을 검사 (열을 배열로 만들어서 재사용)
        for (int j = 0; j < N; j++){
            int[] col = new int[N];
            for (int i = 0; i < N; i++){
                col[i] = map[i][j];
            }
            if (isValid(col, L))
                count++;
        }
        
        System.out.println(count);
    }
    
    // 주어진 도로(행 또는 열)가 경사로를 놓아 지나갈 수 있는 길인지 확인하는 메서드
    private static boolean isValid(int[] road, int L) {
        int N = road.length;
        boolean[] used = new boolean[N];  // 경사로가 이미 놓인 칸 표시
        
        for (int i = 0; i < N - 1; i++) {
            // 높이가 같으면 별다른 처리 없이 넘어감
            if (road[i] == road[i + 1])
                continue;
            
            // 올라가는 경우 : 차이가 1이고, 현재 칸 기준 뒤쪽 L칸이 경사로 조건을 만족하는지 확인
            if (road[i + 1] - road[i] == 1) {
                // 뒤쪽 L칸이 모두 같은 높이인지와 경사로가 이미 사용되었는지 확인
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || road[j] != road[i] || used[j])
                        return false;
                    used[j] = true;
                }
            }
            // 내려가는 경우 : 차이가 -1인 경우, 앞으로 L칸에 대해 조건 확인
            else if (road[i] - road[i + 1] == 1) {
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || road[j] != road[i + 1] || used[j])
                        return false;
                    used[j] = true;
                }
            }
            // 높이 차이가 1보다 크면 경사로를 놓을 수 없음
            else {
                return false;
            }
        }
        return true;
    }
}
