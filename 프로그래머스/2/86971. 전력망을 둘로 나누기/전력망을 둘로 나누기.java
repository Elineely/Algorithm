import java.util.*;
import java.io.*;

class Solution {
    int N;
    int[][] W;
    
    int minDiff = 101;
    
    List<Integer>[] tree;
    
    public int solution(int n, int[][] wires) {
        N = n;
        W = wires;
        
        tree = new List[n + 1];
        for(int i = 1; i <= n; ++i){
            tree[i] = new LinkedList<Integer>();
        }
        for(int i = 0; i < n - 1; ++i){
            // System.out.println(i);
            int v1 = W[i][0];
            int v2 = W[i][1];
            tree[v1].add(v2);
            tree[v2].add(v1);
        }
        
        for (int i = 0 ; i < N - 1; ++i){
            findMinDiff(i);
        }
        
        return minDiff;
    }

    void findMinDiff(int index){
        int v1 = W[index][0];
        int v2 = W[index][1];
        
        boolean visited[] = new boolean[N + 1];
        //v1 트리 크기 찾기
        Queue<Integer> q = new LinkedList<>(); 
        q.add(v1);
        visited[v1] = true;
        int size1 = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            ++size1;
            for(int i = 0; i < tree[curr].size(); ++i){
                int nc = tree[curr].get(i);
                if (curr == v1 && nc == v2)
                    continue; //없는 엣지인셈 침
                if (visited[nc] == true)
                    continue; //이미 방문한 노드
               q.add(nc);
            visited[nc] = true;
            }
        }
        
        if(Math.abs((N - size1) - size1) < minDiff)
            minDiff = Math.abs((N - size1) - size1);
    }//end of solution method
    
}//end of class



/*
모든 전선을 하나씩 끊어보고
작은 트리와 큰 트리의 차이를 비교한다.


*/