import java.util.*;

class Solution {
    Map<Character, Integer> types = new HashMap<>();
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        char[] typeNames = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        int[] scores = {3, 2, 1, 0, 1, 2, 3};
        
        for(char name: typeNames) {
            types.put(name, 0);
        }
        
        char type;
        
        for(int i = 0; i < survey.length; i++) {
            if(choices[i] < 4) {         //동의
                type = survey[i].charAt(0);
            } else if(choices[i] > 4) {  //비동의
                type = survey[i].charAt(1);
            } else {
                continue;
            }
            
            int score = types.get(type) + scores[choices[i] - 1];  //원래값 + 선택지값
            types.put(type, score);
        }
        
        for(int i = 0; i < 8; i += 2) {
            answer += findType(typeNames[i], typeNames[i + 1]);
        }
        
        return answer;
    }
    
    public char findType(char first, char second) {
        if (types.get(first) > types.get(second) 
            || types.get(first) == types.get(second) && first < second) {
            return first;
        }
        
        return second;
    }
}