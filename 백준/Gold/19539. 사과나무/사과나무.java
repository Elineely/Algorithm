import java.util.*;
 
public class Main { 
    static Scanner sc = new Scanner(System.in);
    static int N;
    public static void main(String[] args) {
        N = sc.nextInt();
        int count1 = 0;
        int count2 = 0;
        int sum = 0;
        
        for(int i = 0; i < N; ++i){
            int curr = sc.nextInt();
            if (curr == 0)
                continue;
            count2 += curr / 2;
            count1 += curr % 2;
            sum += curr;
        }
        if(sum == 0){
            System.out.println("YES");
        }
        else if(sum % 3 > 0 || count1 > count2){
                System.out.println("NO");
        }
        else {
            System.out.println("YES");
        }

        // {}
        // // else if(sum % 3 == 0 && count2 > count1){
        //     System.out.println("YES");
        // }
        // else{
        //     System.out.println("NO");
        // }

    } //end of main method

}//end of Main class
