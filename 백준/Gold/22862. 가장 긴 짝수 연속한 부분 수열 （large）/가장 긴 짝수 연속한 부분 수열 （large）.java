import java.util.Scanner;
 
public class Main {
    static int N;
    static int K;

    static Scanner sc = new Scanner(System.in);
	static int[] S ; 
    
    public static void main(String[] args) {
        N = sc.nextInt();
        K = sc.nextInt();
        S = new int[N];
        
        for(int i = 0 ; i < N; ++i){
            S[i] = sc.nextInt();
        }

        int head = 0;
        int tail = 0;
        int kCount = 0;
        int maxLength = 0;
        find : while(tail < N){
            if(S[tail] % 2 == 0)
                tail++;
            else{
                tail++;
                kCount++;
                if(kCount > K){
                    while(kCount > K){
                        if (S[head] % 2 != 0)
                            kCount--;
                        head++;
                    }
                }
            }
            maxLength = Math.max(maxLength, tail - head - kCount);
        }
        System.out.println(maxLength);
    } //end of main method
}//end of Main class