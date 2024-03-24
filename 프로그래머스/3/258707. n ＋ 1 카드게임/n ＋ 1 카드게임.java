import java.util.*;

class Solution {
    private static int[] cards;
    private static int total;
    private static Set<Integer> possessionCard = new HashSet<>();
    private static int[] cardsIndex;
    
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        total = n + 1;
        this.cards = cards;
        int count = 0;
        
        cardsIndex = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            if (i < n/3) {
                possessionCard.add(cards[i]);
                if (possessionCard.contains(total - cards[i])) {
                    count++;
                }
            }
            cardsIndex[cards[i]] = i;
        }
        
        int i = 0;
        int oneCardCount = 0;
        int twoCardCount = 0;
        
        for (i = n/3; i < n; i+=2) {
            int left = cards[i];
            int right = cards[i + 1];
            
            if (possessionCard.contains(total - left)) {  //1. 왼쪽 카드 + 기존에 있는 카드 == n+1 확인
                oneCardCount++;
            } else if (cardsIndex[total - left] < cardsIndex[left]) {  //2. 왼쪽 카드 + 전에 뽑지 않은 카드 == n+1 확인
                twoCardCount++;
            }
            
            if (possessionCard.contains(total - right)) { //1. 오른쪽 카드 + 기존에 있는 카드 == n+1 확인
                oneCardCount++;
            } else if (cardsIndex[total - right] < cardsIndex[left]) {  //2. 오른쪽 카드 + 전에 뽑지 않은 카드 == n+1 확인
                twoCardCount++; 
            }
            
            if (left + right == total) {  //3. 두 카드를 모두 구매하면 카드를 만들 수 있는 경우 기억해두기
                twoCardCount++; 
            }
            
            if (count >= 1) {  //4 - 1. 소유한 카드를 먼저 냄
                count--;
                continue;
            }
            
            if (coin >= 1 && oneCardCount >= 1) {  //4 - 2. 기존 카드 + 후보카드 조합으로 낼 수 있는 경우를 먼저 냄 
                coin--;
                oneCardCount--;
                continue;
            }
            
            if (coin >= 2 && twoCardCount >= 1) { //4 - 3. 카드 두 개 구매해야하는 경우
                coin -= 2;
                twoCardCount--;
                continue;
            }
            
            break;  //5. 더 이상 조합을 만들 수 없는 경우 종료
        }
        
        return (i - n/3) / 2 + 1;
    }
}