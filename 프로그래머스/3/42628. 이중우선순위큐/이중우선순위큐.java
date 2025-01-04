import java.util.*;

class Solution {
    PriorityQueue<Integer> maxHip = 
        new PriorityQueue<>((o1, o2) -> {return o2 - o1;});
    PriorityQueue<Integer> minHip = new PriorityQueue<>();
    
    public int[] solution(String[] operations) {
        StringTokenizer st;
        int count = 0;
        
        for(int i = 0; i < operations.length; ++i){
            st = new StringTokenizer(operations[i], " ");
            String order = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            if(order.equals("I")){
                maxHip.add(num);
                minHip.add(num);
                ++count;
            }
            else{
                if (count == 0)
                    continue;
                if(num == -1)
                    maxHip.remove(minHip.poll());
                else
                    minHip.remove(maxHip.poll());
                --count;
            }
        }
        if(count == 0 || minHip.peek() > maxHip.peek()){
            return new int[]{0, 0};
        }
        return new int[]{maxHip.poll(), minHip.poll()};
    }//end of method
}//end of class