import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> items = new HashMap<>();
        
        for (String[] item: clothes) {
            String key = item[1];
            if (items.containsKey(key)) {
                items.put(key, items.get(key) + 1);
            } else {
                items.put(key, 1);
            }
        }
        
        int result = 1;
        
        for (String item: items.keySet()) {
            result *= (items.get(item) + 1);
        }
        
        return result - 1;
    }
}
/*
->각 종류별 최대 1가지
->오늘과 내일의 의상이 같더라도 1가지 요소를 추가 or 한가지 요소 변경

#1)
head: yellow, green
eye: blue

3*2 - 1 = 5

#2)
face: crow, blue, smoky

4 - 1 = 3


*/