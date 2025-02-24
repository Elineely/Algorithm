import java.util.*;
import java.io.*;
 
public class Main { 
    static int N;
    static int X;
    static int[] arr;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        N = sc.nextInt();
        arr  = new int[N];

        for(int i = 0; i < N; ++i){
            arr[i] = sc.nextInt();
        }
        X = sc.nextInt();

        Arrays.sort(arr);
        int left = 0;
        int right = N - 1;
        int count = 0;
        while(left < right){
            int sum = arr[left] + arr[right];
            if(sum > X){
                right--;
            }else if (sum < X)
                left++;
            else { //같은 경우
                right--;
                left++;
                count++;
            }
        }
        System.out.println(count);
    } //end of main method

}//end of Main class
