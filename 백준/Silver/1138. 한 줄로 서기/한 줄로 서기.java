import java.util.*;
import java.io.*;


class Main{

    static ArrayList<Integer> line = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int arr[] = new int[N];

        for (int i = 0 ; i < N; ++i){
            arr[i] = sc.nextInt();
        }

        for(int i = N - 1; i >= 0; --i){
            line.add(arr[i], i + 1);   
        }
        
       for(int i = 0; i < N; ++i){
            System.out.print(line.get(i) + " ");
        }
    }//end of main
}//end of class
