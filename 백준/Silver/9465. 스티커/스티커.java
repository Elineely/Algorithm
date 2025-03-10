import java.util.*;

import java.io.*;
 
public class Main { 
    static Scanner sc = new Scanner(System.in);
    static int T;
    static int N;
    public static void main(String[] args) {
        T = sc.nextInt();
        for(int t = 1; t <=T; ++t){
            N = sc.nextInt();
            int[][] arr = new int[2][N];
            int[][] dp = new int[2][N];
            for (int i = 0 ; i< 2; ++i){
                for(int  k = 0; k < N; ++k){
                    arr[i][k] = sc.nextInt();
                }
            }

            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];

            for(int i = 1; i < N; ++i){
                //for(int k = 0; k < 2; ++k){
                    // if(k == 0){
                        dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + arr[0][i]); 
                    // }
                    // else {
                        // dp[1][i] = Math.max(dp[0][i], Math.max(dp[0][i - 1] + arr[1][i], dp[1][i - 1])); 
                        dp[1][i] = Math.max(dp[0][i - 1] + arr[1][i], dp[1][i - 1]); 
                    // }
                    // System.out.println(dp[k][i]);
               // }
            }

            System.out.println(Math.max(dp[0][N - 1], dp[1][N - 1]));
        }

    } //end of main method

}//end of Main class
