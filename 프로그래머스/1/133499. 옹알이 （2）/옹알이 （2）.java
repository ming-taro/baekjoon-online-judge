class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] words = { "aya", "ye", "woo", "ma" };
                
        for (String bab: babbling) {
            int index = 0;
            int length = bab.length();
            int prev = -1;
            boolean flag = false;
            
            while (index < length) {
                flag = false;
                for (int i = 0; i < 4; i++) { // 4개의 옹알이 중 있는지 검사
                    String word = words[i];
                    
                    if (index + word.length() <= length
                        && prev != i
                        && (bab.substring(index, index + word.length())).equals(word)) {
                        index += word.length();
                        prev = i;
                        flag = true;
                        break;
                    }
                }
                
                if (flag == false) {
                    break;
                }
            }
            if (flag) {
                answer++;
            }
        }
        
        return answer;
    }
}