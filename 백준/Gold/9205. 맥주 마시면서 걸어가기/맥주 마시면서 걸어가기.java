import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            // 전체 노드: 시작점, 편의점 n개, 페스티벌 (총 n+2)
            int[][] coords = new int[n + 2][2];
            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                coords[i][0] = Integer.parseInt(st.nextToken());
                coords[i][1] = Integer.parseInt(st.nextToken());
            }
            
            // BFS를 위한 준비
            boolean[] visited = new boolean[n + 2];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0); // 시작점 인덱스
            visited[0] = true;
            boolean reachable = false;
            
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                // 페스티벌에 도달하면 탐색 종료
                if (curr == n + 1) {
                    reachable = true;
                    break;
                }
                for (int next = 0; next < n + 2; next++) {
                    if (!visited[next] && getDistance(coords[curr], coords[next]) <= 1000) {
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }
            
            sb.append(reachable ? "happy" : "sad").append("\n");
        }
        System.out.print(sb);
    }
    
    // 맨해튼 거리를 계산하는 메서드
    private static int getDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
