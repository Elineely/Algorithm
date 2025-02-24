import java.util.*;
import java.io.*;
 
public class Main { 
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String str = sc.next();
        int sizeOfA = 0;
        int answer = 1001;
        for(int i = 0 ; i < str.length(); ++i){
            if(str.charAt(i) =='a')
                ++sizeOfA;
        }

        for(int i = 0 ; i < str.length(); ++i){
            int countB = 0;
            for(int k = 0; k < sizeOfA; ++k){
                int index = (i + k) % str.length();
                if(str.charAt(index) == 'b')
                    countB++;
            }
            if (countB < answer)
                answer = countB;
        }

        System.out.println(answer);
    } //end of main method

}//end of Main class
