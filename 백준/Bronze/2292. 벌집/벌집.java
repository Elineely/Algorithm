import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        if (N == 1){ 
            System.out.println("1");
            return;
        }
        int count = 1;
        int angle = 6;
        int now = 1;

        while(true){
            now += (count * angle);
            count++;
            if (N <= now) break; 
        }

        System.out.println(count);
    }
}