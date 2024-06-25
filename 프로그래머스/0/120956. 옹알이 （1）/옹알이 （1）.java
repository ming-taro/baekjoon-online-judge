class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] words = { "aya", "ye", "woo", "ma" };
                
        for (String bab: babbling) {
            int index = 0;
            int length = bab.length();
            boolean flag = false;
            
            while (index < length) {
                flag = false;
                for (String word: words) { // 4개의 옹알이 중 있는지 검사
                    if (index + word.length() <= length
                        && (bab.substring(index, index + word.length())).equals(word)) {
                        index += word.length();
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