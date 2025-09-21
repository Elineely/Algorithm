class Solution {
    public String solution(String myString) {
        int len = myString.length();
        char[] temp = new char[len];
        
        for(int i = 0; i < len; ++i){
            if(myString.charAt(i) < 'l')
                temp[i] = 'l';
            else
                temp[i] = myString.charAt(i);
        }
        
        String answer = new String(temp);
        return answer;
    }
}
