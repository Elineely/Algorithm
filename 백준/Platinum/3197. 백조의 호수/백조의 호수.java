import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
 
    static class Node {
        int x;
        int y;
 
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
 
 
    // 백조 인접 여부
    static boolean check() {
 
 
        Queue<Node> newQ = new ArrayDeque<>();
        while (!Q.isEmpty()) {
 
            int size = Q.size();
            while (size-- > 0) {
                Node cur = Q.poll();
                int x = cur.x;
                int y = cur.y;
 
                if (cur.x == end.x && cur.y == end.y) {
                    return true;
                }
 
 
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
 
                    if (isOut(nx, ny) || visited[nx][ny]) {
                        continue;
                    }
 
                    visited[nx][ny] = true;
                    if (G[nx][ny] == 'X') {
                        newQ.add(new Node(nx, ny));
                        continue;
                    }
                    Q.add(new Node(nx, ny));
                }
 
            }
        }
        Q = newQ;
        return false;
 
    }
 
    static boolean isOut(int x, int y) {
        if (x < 0 || y < 0 || x >= R || y >= C) {
            return true;
        }
        return false;
 
    }
 
 
    static void melt() {
        Queue<Node> melting = new ArrayDeque<>();
 
        while (!water.isEmpty()) {
            Node cur = water.poll();
            int x = cur.x;
            int y = cur.y;
 
 
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
 
                if (isOut(nx, ny) || G[nx][ny] != 'X') {
                    continue;
                }
 
                G[nx][ny] = '.';
                melting.add(new Node(nx, ny));
            }
        }
        water = melting;
    }
 
    static Node start, end;
    static Queue<Node> Q;
    static Queue<Node> water;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int R, C;
    static char[][] G;
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[R][C];
        G = new char[R][C];
 
        water = new ArrayDeque<>();
        Q = new ArrayDeque<>();
 
 
        for (int i = 0; i < R; i++) {
            G[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (G[i][j] == 'L') {
                    if (start == null) {
                        start = new Node(i, j);
                        visited[i][j] = true;
                        Q.add(new Node(i, j));
                    } else {
                        end = new Node(i, j);
                    }
                    water.add(new Node(i, j));
                } else if (G[i][j] == '.') {
                    water.add(new Node(i, j));
                }
            }
        }
 
 
        int day = 0;
 
        while (true) {
            // 백조 인접 여부 확인
            if (check()) {
                System.out.println(day);
                break;
            }
            //빙하 녹이기(물 확산)
            melt();
            day++;
        }
 
    }
}