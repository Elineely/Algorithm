import java.util.*;
import java.io.*;


public class Main {

	static Scanner sc = new Scanner(System.in);
	static long T;
	static int N;
	static int M;

	static long[] A;
	static long[] B;

	static long count = 0;

	public static void main(String[] args) throws IOException{
		T = sc.nextInt();
		N = sc.nextInt();

		A = new long[N];
		for(int i = 0 ; i < N; ++i){
			A[i] = sc.nextInt();
		}
		M = sc.nextInt();
		B = new long[M];
		for(int i = 0 ; i < M; ++i){
			B[i] = sc.nextInt();
		}

		int numOfSa = N * (N + 1) / 2;
		int index = 0;
		long[] sa = new long[numOfSa];
		for(int i =  0; i < N; ++i){
			long sum = 0;
			for(int k = i; k < N ; ++k){
				sum += A[k];
				sa[index++] = sum; 
				// System.out.print(sa[index - 1]);
			}
		}
		// System.out.println();

		int numOfSb = M * (M + 1) / 2;
		index = 0;
		long[] sb = new long[numOfSb];
		for(int i =  0; i < M; ++i){
			long sum = 0;
			for(int k = i; k < M ; ++k){
				sum += B[k];
				sb[index++] = sum; 
				// System.out.print(sb[index - 1]);

			}
		}
		// System.out.println();


		Arrays.sort(sa);
		Arrays.sort(sb);
		int left = 0;
		int right = numOfSb - 1;
		while(left < numOfSa && right > -1){
			long currSum = sa[left] + sb[right];
			if(currSum > T){
				right--;
			}
			else if (currSum < T){
				left++;
			}
			else{ //T와 같은 경우

				long countA = 0;
				long countB = 0;
				long targetA = sa[left];
				long targetB = sb[right];
				// System.out.println("ta : " + targetA + " , tb : " + targetB + " , right : "  + right);
				while(left < numOfSa && sa[left] == targetA){
					left++;
					countA++;
				}

				while(right > - 1 && sb[right] == targetB){
					right--;
					countB++;
				}
				count += countA * countB;
			}
		}//end of while
		System.out.println(count);
	}//end of main method

}//end of main class
