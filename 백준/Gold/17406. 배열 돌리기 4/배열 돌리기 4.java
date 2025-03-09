import java.util.*;
import java.io.*;
 
public class Main { 
    static class Info{
        int r;
        int c;
        int s;

        Info(int r, int c, int s){
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    
    
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int M;
    static int K;
    static Info[] candidates;
    static boolean[] visited;
    static int min= 5001;

    static int[][] A;
    public static void main(String[] args) {

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        A = new int[N + 1][M + 1];

        for(int i = 1 ; i <= N; ++i){
            for(int k = 1; k <= M; ++k){
                A[i][k] = sc.nextInt();
            }
        }
        // printMap(A);

        candidates = new Info[K];
        for(int i = 0; i <K; ++i){
            candidates[i] = new Info(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        visited = new boolean[K];
        for (int i = 0 ; i < K; ++i){
            if(!visited[i]){
                visited[i] = true;
                int[][] temp = new int[A.length][A[0].length];
                for(int k = 0; k <= N; ++k){
                    System.arraycopy(A[k], 0, temp[k], 0, M + 1);
                }
                rotate(temp, candidates[i]);
                makeCombi(1, temp);
                visited[i] = false;
            }
        }

        System.out.println(min);
    
    } //end of main method

    static void rotate(int[][] map, Info curr){
        for(int i = 1; i <= curr.s; ++i){
        // for(int i = curr.s; i >= 1; --i){
            // System.out.println("iiiii : " + i  + " !!!!!!!!!!!!!!!!!!!!!!!!!!`");
            //위
            // System.out.println(" r, c : " + curr.r + " , " + curr.c + " , s : " + i);
            int tempUp = map[curr.r - i][curr.c + i];
            for (int x = curr.c + i; x > curr.c - i; --x){
                map[curr.r - i][x] = map[curr.r -i][x - 1];
            }
            // printMap(map);


            //오른쪽
            int tempRight = map[curr.r + i][curr.c + i];
            for (int y = curr.r + i; y > curr.r - i + 1; --y){
                map[y][curr.c + i] = map[y - 1][curr.c + i];
            }
            map[curr.r -i + 1][curr.c + i] = tempUp;

            // printMap(map);


            //아래
            int tempDown = map[curr.r + i][curr.c -i];
            for (int x = curr.c - i; x < curr.c + i - 1; x++){
                map[curr.r + i][x] = map[curr.r + i][x + 1];
            }
            map[curr.r + i][curr.c + i - 1] = tempRight;
            // printMap(map);

            //왼쪽
            for (int y = curr.r - i; y < curr.r + i - 1; ++y){
                map[y][curr.c - i] = map[y + 1][curr.c - i];
            }
            map[curr.r + i - 1][curr.c - i] = tempDown;
            // printMap(map);

        }

        // printMap(map);
    }

    // static void printMap(int[][] map){
    //     System.out.println("------------------------------");
    //     for(int i = 0; i <= N; ++i){
    //         for(int k = 0 ; k <= M; ++k){
    //             System.out.print(map[i][k] + " ");
    //         }
    //         System.out.println();
    //     }
    //     System.out.println("------------------------------");
    // }

    static void makeCombi(int curr, int[][] currMap){
        if (curr == K){
            getMin(currMap);
            // printMap(currMap);
            return;
        }
        for(int i = 0; i < K; ++i){
            if(!visited[i]){
                visited[i] = true;
                int[][] temp = new int[A.length][A[0].length];
                for(int k = 0; k <= N; ++k){
                    System.arraycopy(currMap[k], 0, temp[k], 0, M + 1);
                }
                rotate(temp, candidates[i]);
                makeCombi(curr + 1, temp);
                visited[i] = false;
            }
        }
    }

    static void getMin(int[][]map){
        // int temp = 1000;
        for(int i = 1; i <= N; ++i){
            int sum = 0;
            for (int k = 1 ; k <= M; ++k){
                sum += map[i][k];
            }
            if(sum < min)
                min = sum;
        }
    }

}//end of Main class
 