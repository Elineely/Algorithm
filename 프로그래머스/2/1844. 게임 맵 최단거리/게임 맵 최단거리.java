import java.util.*;

class Solution {
    
    class Info{
        int cost;
        int y;
        int x;
        
        public Info(int cost, int y, int x){
            this.cost = cost;
            this.y = y;
            this.x = x;
        }
    }
    
    // PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> (a.cost - b.cost));
   int[] dy = {-1, 1, 0, 0};
    int[] dx = {0,0,-1, 1};
        Queue<Info> pq = new LinkedList<>();
    public int solution(int[][] maps) {
        int answer = 0;
        int N = maps.length;
        int M = maps[0].length;
        boolean[][] visited = new boolean[N][M];
        pq.offer(new Info(1,0,0));
        visited[0][0] = true;
        
        while(!pq.isEmpty()){
            Info curr = pq.poll();
            if(curr.y == N -1 && curr.x == M - 1){
                answer = curr.cost;
                break;
            }
            for(int i = 0 ; i < 4; ++i){
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];
                if(ny <0 || ny >= N || nx < 0 || nx >= M)
                    continue;
                if (maps[ny][nx] != 1)
                    continue;
                if (visited[ny][nx])
                    continue;
                pq.offer(new Info(curr.cost + 1, ny, nx));
                visited[ny][nx] = true;
            }
        }
        if (answer == 0)
            answer = -1;
        return answer;
    }//method
}//class
// 0 = 벽, 1 = 길

/*
가장 빠른길 찾기
비용 위치x, 위치 y
priorityqueue

최소 비용 리턴

도착할 수 없을 때는 -1 return

n * m 

*/