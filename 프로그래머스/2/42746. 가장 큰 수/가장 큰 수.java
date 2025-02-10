import java.util.*;
import java.io.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arr = new ArrayList<>();
        for(int num : numbers){
            arr.add(Integer.toString(num));
        }
        
        Collections.sort(arr, (a, b) -> (b + a).compareTo(a + b));
        for(String a : arr){
            sb.append(a);
        }
        
        if (sb.toString().charAt(0) == '0') {
            return "0";
        }
        
        return sb.toString();
    }
}
/*
9:23~
이어 붙여 만들수 있는 가장 큰 수 
문자열 알파벳 정렬해서 풀기.

*/