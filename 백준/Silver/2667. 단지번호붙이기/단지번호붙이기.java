import java.util.*;
import java.io.*;
 
public class Main { 
    static Scanner sc = new Scanner(System.in);
    static int[][] map;
    static int N;
    static int[] count = new int[700];
    static int num = 0;

    public static void main(String[] args) {
        N = sc.nextInt();
        map = new int[N][N];

        for(int i = 0 ; i < N; ++i){
            String line = sc.next(); 
            for(int k = 0 ; k < N; ++k){
                map[i][k] = line.charAt(k) - '0';
            }
        }

        for(int i = 0 ; i < N; ++i){
            for(int k = 0 ; k < N; ++k){
                if(map[i][k] == 1){
                    ++num;
                    countDanji(i, k);
                }
            }
        }
        printAnswer();
    } //end of main method

    static void printAnswer(){
        System.out.println(num);
        // Arrays.sort(count);
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 1 ; i <= num; ++i ){
            ans.add(count[i]);
        }
        Collections.sort(ans);
        for(Integer a : ans){
            System.out.println(a);
        }
    }

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    static void countDanji(int y, int x){
        map[y][x] = 0;
        count[num]++;
        for(int i = 0 ; i < 4; ++i){
            int nY = dy[i] + y;
            int nX = dx[i] + x;
            if (nY < 0 || nX < 0 || nY >= N || nX >= N || map[nY][nX] == 0)
                continue;
            countDanji(nY, nX);
        }
    }
}//end of Main class
