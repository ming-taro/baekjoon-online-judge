import java.util.*;

class Solution {
    private static Map<String, Integer> orderList;
    private static Set<Integer> courseCount;
    private static Map<Integer, Integer> courseIndex;
    
    public String[] solution(String[] orders, int[] course) {
        orderList = new HashMap<>();
        courseCount = new HashSet<>();
        courseIndex = new HashMap<>();

        for (int i = 0; i < course.length; i++) {
            courseCount.add(course[i]);
            courseIndex.put(course[i], i);
        }
        
        for (int i = 0; i < orders.length; i++) {
            char[] newOrder = orders[i].toCharArray();
            Arrays.sort(newOrder);
            orders[i] = new String(newOrder);
        }
        
        int[] maxCount = new int[course.length];
        
        for (String order: orders) {
            dfs("", order, 0);
        }
        
        for (String key: orderList.keySet()) {
            if (orderList.get(key) >= 2) {
                int index = courseIndex.get(key.length());
                maxCount[index] = Math.max(maxCount[index], orderList.get(key));
            }
        }

        List<String> result = new ArrayList<>();
        
        for (String key: orderList.keySet()) {
            int index = courseIndex.get(key.length());
            if (orderList.get(key) == maxCount[index]) {
                result.add(key);
            }
        }
        
        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        Arrays.sort(answer, (o1, o2) -> o1.compareTo(o2));
        
        return answer;
    }
    
    private static void dfs(String result, String order, int current) {
        if (courseCount.contains(result.length())) {
            int count = 1;
            if (orderList.containsKey(result)) {
                count = orderList.get(result) + 1;
            }
            orderList.put(result, count);
        }
        
        if (current == order.length()) return;
        
        for (int i = current; i < order.length(); i++) {
            dfs(result + order.charAt(i), order, i + 1);
        }
    }
}