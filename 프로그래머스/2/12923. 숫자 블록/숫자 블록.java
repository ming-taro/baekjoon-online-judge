class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int) (end - begin) + 1];
        
        int number = (int) begin;
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = 1;
            
            for (int j = 2; j <= Math.sqrt(number); j++) {
                if (number % j == 0) {//숫자 블록이 될 수 있는 수는 j와 number%j
                    if (number / j <= 10000000) {//처음으로 나누어 떨어지는 경우 -> j는 가장 작은 약수, number%j는 가장 큰 약수
                        answer[i] = number / j;//가장 큰 약수가 숫자 블록의 최댓값을 넘지 않는다면 정답
                        break;
                    } else {
                        answer[i] = j; //가장 큰 약수가 블록의 최댓값을 넘는다면 우선 j를 넣어둠 -> 이후 연산을 반복하며 최댓값을 갱신함
                    }
                }
            }
            number++;
        }
        
        if (begin == 1) {
            answer[0] = 0;
        }
        
        return answer;
    }
}