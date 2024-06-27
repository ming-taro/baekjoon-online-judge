import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> participantSet = new HashMap<>();
        Set<String> names = new HashSet<>();
        
        for (String name: participant) {
            int count = 1;
            if (participantSet.containsKey(name)) {
                count += participantSet.get(name);
            }
            names.add(name);
            participantSet.put(name, count);
        }
        
        for (String name: completion) {
            participantSet.put(name, participantSet.get(name) - 1);
            if (participantSet.get(name) == 0) {
                names.remove(name);
            }
        }
        
        for (String name: names) {
            return name;
        }
        
        return "";
    }
}
/*
한 명의 선수 제외, 모두 완주
participant - 마라톤 참가 선수 이름
completion - 완주 선수
*/