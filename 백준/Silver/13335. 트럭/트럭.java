import java.util.*;
import java.io.*;
 
public class Main { 
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int W;
    static int L;

    static Queue<Integer> bridge = new LinkedList<>();
    static Queue<Integer> truck = new LinkedList<>();
    public static void main(String[] args) {
        int time = 0;
        
        N = sc.nextInt();
        W = sc.nextInt();
        L = sc.nextInt();
        
        for(int i = 0; i < N; ++i){
            truck.add(sc.nextInt());
        }

        for(int i = 0; i < W; ++i){
            bridge.add(0);
        }

        int weight = 0;
        while(!truck.isEmpty()){    
            weight -= bridge.poll();
            int next = truck.peek();
            if(weight + next > L)
                bridge.add(0);
            else{
                int curr = truck.poll();
                bridge.add(curr);
                weight += curr;
            }
            ++time;
        }
        time += W;

        System.out.println(time);
    } //end of main method

}//end of Main class
 