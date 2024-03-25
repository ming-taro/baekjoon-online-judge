import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int friendCount = friends.length;
        
        Map<String, Integer> indexs = new HashMap<>();
        int[][] receiveCount = new int[friendCount][friendCount];
        int[][] giveCount = new int[friendCount][friendCount];
        int[] giftCount = new int[friendCount];  //선물지수
        
        for (int i = 0; i < friendCount; i++) {
            indexs.put(friends[i], i);
        }
        
        for (String gift: gifts) {
            String[] friend = gift.split(" ");
            int A = indexs.get(friend[0]);  //A -> B
            int B = indexs.get(friend[1]);
            giveCount[A][B]++;
            receiveCount[B][A]++;
            giftCount[A]++;
            giftCount[B]--;
        }
        
        int[] result = new int[friendCount];
        
        for (int i = 0; i < friendCount; i++) {
            for (int j = i + 1; j < friendCount; j++) {
                if (Math.abs(giveCount[i][j] - giveCount[j][i]) > 0) {  //A>B or A<B
                    if (giveCount[i][j] > giveCount[j][i]) {
                        result[i]++;
                    } else {
                        result[j]++;
                    }
                } else {
                    if (giftCount[i] > giftCount[j]) {
                        result[i]++;
                    } else if (giftCount[i] < giftCount[j]) {
                        result[j]++;
                    }
                }
            }
        }
        
        for (int gift: result) {
            answer = Math.max(answer, gift);
        }
        
        return answer;
    }
}

/*
if A, B 선물을 주고 받은 기록O:
    A가 준 선물 > B -> 다음달 A가 B에게 선물을 하나 받음
else 똑같이 주고받음 or A, B 선물을 주고 받은 기록X:
    if 선물지수 A == B:
        X
    else:
        A 선물 지수 > B -> A가 B에게 선물을 하나 받음

->선물지수 = 이번달 친구에게 준 선물 - 받은 선물
->return 선물을 가장 많이 받을 사람의 선물 수
*/