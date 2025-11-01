class Solution {
    boolean[] visited;
    int N ;
    
    public void dfs(int curr, int[][] computers){
        for(int i = 0; i < N; ++ i){
            if(computers[curr][i] == 0 || visited[i])
                continue;
            visited[i] = true;
            dfs(i, computers);
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        N = n;
        visited = new boolean[N];
        for(int i = 0 ; i < N; ++i){
            if(visited[i])
                continue;
            answer++;
            visited[i] = true;
            dfs(i, computers);
        }
        
        return answer;
    }
}