import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        int n = survey.length;
        char type;
        
        Map<Character, Integer> userChoices = new HashMap<Character, Integer>();
        int[] scores = {3, 2, 1, 0, 1, 2, 3};
        int score;
        
        char[] standard = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        
        for (int index = 0; index < 8; index++) {
            userChoices.put(standard[index], 0);
        }
        
        for (int index = 0; index < n; index++) {            
            if (choices[index] == 4){
                continue;
            }
            else if (choices[index] < 4) {
                type = survey[index].charAt(0);
            }
            else{
                type = survey[index].charAt(1);
            }
            
            score = scores[choices[index] - 1];
            
            userChoices.put(type, userChoices.get(type) + score);
        }
        
        userChoices.forEach((key, value) -> {
            System.out.println(key + " / " + value);
        });
        
        int firstChoice, secondChoice;
        
        for (int index = 0; index <= 6; index += 2) {
            firstChoice = userChoices.get(standard[index]);
            secondChoice = userChoices.get(standard[index + 1]);
            
            if (firstChoice >= secondChoice) {
                answer += standard[index];
            }
            else {
                answer += standard[index + 1];
            }
        }
        
        return answer;
    }
}