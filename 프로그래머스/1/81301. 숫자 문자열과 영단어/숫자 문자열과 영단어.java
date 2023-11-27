import java.util.*;

class Solution {
    public int solution(String s) {
        Map<String, Integer> numbers = createNumberWord();
        
        for(String number: numbers.keySet()){
            // System.out.println(number + "/" + numbers.get(number));
            s = s.replaceAll(number, Integer.toString(numbers.get(number)));
        }
        
        System.out.println(s);
        
        return Integer.parseInt(s);
    }
    
    Map<String, Integer> createNumberWord() {
        Map<String, Integer> numbers = new HashMap<>();
        
        numbers.put("zero", 0);
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);
        numbers.put("five", 5);
        numbers.put("six", 6);
        numbers.put("seven", 7);
        numbers.put("eight", 8);
        numbers.put("nine", 9);
        
        return numbers;
    }
}

//네오 -> 프로도: 숫자를 건낼 때, 일부 자릿수를 영단어로 바꾼 카드를 건네주면 원래 숫자 찾기
