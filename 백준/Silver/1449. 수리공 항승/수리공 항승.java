import java.util.*;
import java.io.*;
 
public class Main { 
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int L;
    static int[] arr;

    public static void main(String[] args) {
        N = sc.nextInt();
        L = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = sc.nextInt();     
        }
        Arrays.sort(arr);
        
        int left = arr[0];
        int count = 1;
        for(int i = 1; i < N; ++i){
            if (arr[i] - left < L)
                continue;
            // System.out.println(arr[i] + " " + count);
            ++count;
            left = arr[i];
        }
        System.out.println(count);
    } //end of main method

}//end of Main class
 