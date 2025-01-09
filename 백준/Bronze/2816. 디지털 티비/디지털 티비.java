import java.util.*;
import java.io.*;

class Main {
    
    static Map<String, Integer> map = new HashMap<>();
    
    static String[] list = new String[100];
    
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args){

        map.put("KBS1", 0);
        map.put("KBS2", 1);
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int location1 = 0;
        int location2 = 0;
       
        for (int i =  0; i < N; ++i){
            list[i] = sc.next();
            if (list[i].equals("KBS1")) location1 =  i;
            else if (list[i].equals("KBS2")) location2 = i;
        }

        //1먼저 올리고
        if (location1 != 0){
            for(int i = 0; i < location1; ++i){
                sb.append("1");
            }
            for(int i = 0; i <location1; ++i){
                sb.append("4");
            }
            if(location1 > location2)
                location2++;
        }
        //2를 올린다
        if (location2 != 1){
            for(int i = 0; i < location2; ++i){
                sb.append("1");
            }
            for(int i = 0; i <location2 - 1; ++i){
                sb.append("4");
            }
        }
        System.out.println(sb.toString());
    }
}
//