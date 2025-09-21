import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        
        for(int i : scoville)
            pQ.offer(i);
        
        int curr = pQ.poll();
        
        while(curr < K){
            answer++;
            // if(pQ.isEmpty())
            //     return -1;
            int another = pQ.poll();
            // System.out.println("curr : " + curr);
            // System.out.println("another : " + another);
            // System.out.println("=================" + answer);
            
            pQ.offer(curr + (another * 2));
            curr = pQ.poll();
            
            if(curr >= K)
                break;
            if(pQ.isEmpty()){
                answer = -1;
                break;
            }//음식이 하나만 남음. 더이상 진행 불가
        }
        
        return answer;
    }
}

/*
2 <= 음식갯수 <= 백만
0 <= K <= 십억
0 <= 스코빌 <= 백만
모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우  -1

스코빌 지수가 가장 낮은 두개 음식 
--> 한 음식 = 가장 맵지 않은 음식의 스코빌 지수 + (걔보단 매운 음식 * 2)
    
    섞어야 하는 최소 횟수를 return
    
    
*/
