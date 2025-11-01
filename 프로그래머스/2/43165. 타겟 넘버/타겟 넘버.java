class Solution {
    int N = 0;
    int answer = 0;
    int[] origin;
    
    void dfs(int sum, int k, int target){
        if(k == N){
            if(sum == target)
                answer++;
            return;
        }
        dfs( sum - origin[k], k + 1, target);
        dfs(sum + origin[k], k + 1, target);
    }
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        N = numbers.length;
        origin = numbers;
        dfs(0,0,target);
        return answer;
    }//end of func
    
}//end of class