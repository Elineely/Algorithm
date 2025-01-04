class Solution {
    int dy[] = {0, 1, 0, -1}; //오, 아, 왼, 위 순으로 순회
    int dx[] = {1, 0, -1, 0};
    
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        
        int y = 0;
        int x = 0;
        int index = 0;
        for (int num= 1; num <= n * n; ++num){
            answer[y][x] = num;
            int ny = y + dy[index];
            int nx = x + dx[index];
            if(ny < 0 || ny >= n || nx < 0 || nx >= n || answer[ny][nx] > 0){
                index = (index + 1) % 4;
                ny = y + dy[index];
                nx = x + dx[index];
            }
            y = ny;
            x = nx;
        }        
        
        return answer;
    }
}