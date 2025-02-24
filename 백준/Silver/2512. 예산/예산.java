import java.util.*;
import java.io.*;
 
public class Main { 
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int M;
    static int[] arr;
    static int answer;

    public static void main(String[] args) {
        N = sc.nextInt();
        arr = new int[N];
        long sum = 0;
        int max = 0;
        for(int i = 0 ; i < N ; ++i){
            arr[i] = sc.nextInt();
            sum += arr[i];
            if(max < arr[i])
                max = arr[i];
        }
        M = sc.nextInt();
        if(sum <= M){
            System.out.println(max);
            return;
        }

        int left = M / N;
        // int left = 0;
        int right = M;
        // int right = M;
        while(left <= right ){
            // System.out.println("l : " + left + " , r : " + right);
            int mid = (right + left) / 2;
            if (getSum(mid) <= M){
                answer = Math.max(mid, answer);
                left = mid + 1;
                if ( getSum(mid) == M)
                    break;
            }
            else{    // 예산 초과시 상한 내림
                right = mid - 1;
            }
        }
        System.out.println(answer);
    } //end of main method

    static long getSum(int max){
        long sum = 0;
        for(int a : arr){
            if(a > max)
                sum += max;
            else
                sum += a;
        }
        return sum;
    }
}//end of Main class
