import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String str = sc.next();
       
        int[] count = new int[26];

        int curr = 0;
        for (int i = 0 ; i < str.length(); ++i){
            char c = str.charAt(i);
            if (c < 'a')
                curr = c - 'A';
            else
                curr = c - 'a';
            
            count[curr]++;
        }

        int max = -1;
        int index = -1;
        boolean flag= false;

        char answer= '.';
        for (int i = 0 ; i < 26; ++i){
            if (count[i] > max){
                max = count[i];
                index = i;
                flag = false;
            }
            else if (count[i] == max){
                flag = true;
            }
        }
        if (flag == true)
            answer = '?';
        else 
            answer =(char)((int)'A' + index);
        System.out.println(answer);
    }
}