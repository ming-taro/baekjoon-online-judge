class Solution {
    public int solution(String s) {
        int answer = 0;
        int index = 0;
        
        while (index < s.length()) {
            char start = s.charAt(index);
            int xCount = 0;
            int count = 0;
            boolean isEnd = false;
            
            for (; index < s.length(); index++) {
                if (s.charAt(index) == start) {
                    xCount++;
                } else {
                    count++;
                }
                if (xCount == count) {  //두 횟수가 같아지는 순간 -> 다음 위치부터 다시 탐색 + count
                    index++;
                    answer++;
                    isEnd = true;
                    break;
                }
            }
            
            if (isEnd == false){ //마지막 위치까지 도달했음에도 종료 조건을 만족X -> 마지막 단어 횟수를 추가로 count + while종료
                answer++;
                break;
            }
        }
        
        return answer;
    }
}