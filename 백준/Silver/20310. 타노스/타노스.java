import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int count0 = 0;
        String S = sc.next();

        for (int i = 0 ; i < S.length(); ++i){
            char c = S.charAt(i);
            sb.append(c);
            if (c =='0')
                count0++;
        }
        int count1 = (S.length() - count0) / 2;
        count0 /= 2;
        
        //1 앞에서 부터 0은 뒤에서 부터 삭제제
        for (int i = 0 ; i < S.length(); ++i){
            if(sb.charAt(S.length() - i - 1) == '0' && count0 > 0){
                sb.setCharAt(S.length() - i - 1, '2');
                --count0;
            }
            if(sb.charAt(i) == '1' && count1 > 0){
                sb.setCharAt(i, '2');
                --count1;
            }
            if(count0 < 1 && count1 < 1) break;
        }

        for(int i = 0 ; i < sb.length(); ++i){
            if(sb.charAt(i) == '2')
                continue;
            System.out.print(sb.charAt(i));
        }
    }// end of main
}// end of class
