import java.util.*;
import java.io.*;
 
public class Main { 
    static Scanner sc = new Scanner(System.in);
    static Set<String> s = new HashSet<>();
    public static void main(String[] args) {
        String S = sc.next();
        
        for(int i = 0; i < S.length(); ++i){
            StringBuffer sb = new StringBuffer();
            for(int k = i; k < S.length(); ++k){
                sb.append(S.charAt(k));
                s.add(sb.toString());
            }
        }

        System.out.println(s.size());
    } //end of main method

}//end of Main class