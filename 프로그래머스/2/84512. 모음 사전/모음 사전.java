class Solution {
    private static String[] words = {"A", "E", "I", "O", "U"};
    private static int answer = 0;
    private static int count = 0;
    
    public int solution(String word) {
        dfs(0, word, "");
        return answer;
    }
    
    private static void dfs(int deep, String word, String result) {
        if (result.equals(word)) {
            answer = count;
            return;        
        }
        
        if (deep == 5) {
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            count++;
            dfs(deep + 1, word, result + words[i]);
        }
    }
}
/*
A
AA
AAA
AAAA
처음 A -> 1, 2, 3, 4개

[AAAA_]
A가 4개 고정 + 알파벳 5개
AAAAA
AAAAE (6)
AAAAI
AAAAO
AAAAU

[AAAX_] (X: E, I, O, U)
A가 3개 고정 + 알파벳 5개 + (빈 1개 + 알파벳 5개)
AAAE (10)
AAAEA
AAAEE
AAAEI
AAAEO
AAAEU
...
AAAUU

[AAX_]
A가 2개 고정 + 알파벳 5개 + (빈 1개 + 알파벳 5개)
AAE
AAEA
AAEE
AAEI
AAEO
AAEU
*/