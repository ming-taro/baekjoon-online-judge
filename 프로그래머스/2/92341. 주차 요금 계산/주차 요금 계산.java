import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> cars = new HashMap<>();
        Map<String, Integer> totalTime = new HashMap<>();
        List<String> carsNumber = new ArrayList<>();
        
        for (int i = 0; i < records.length; i++) {
            String[] info = records[i].split(" ");
            String time = info[0];
            String car = info[1];
            
            if (info[2].equals("IN")) {  //입차
                cars.put(car, convertSec(time));
            } else { //출차
                int total = convertSec(time) - cars.get(car);
                cars.remove(car);  //출차한 차량의 기록을 지움(다음 입차 기록이 있을 수 있기 때문)
                
                if (totalTime.containsKey(car)) {
                    totalTime.put(car, totalTime.get(car) + total);
                } else {
                    totalTime.put(car, total);
                }
            }
        }
        
        for (String car: cars.keySet()) { //입차기록만 있는 차량
            int total = convertSec("23:59") - cars.get(car);
            
            if (totalTime.containsKey(car)) {
                totalTime.put(car, totalTime.get(car) + total);
            } else {
                totalTime.put(car, total);
            }
        }
        
        for (String car: totalTime.keySet()) {
            carsNumber.add(car);
        }
        Collections.sort(carsNumber); //차량 번호 오름차순 정렬
        
        int[] answer = new int[carsNumber.size()];
        System.out.println(totalTime);
        
        for (int i = 0; i < answer.length; i++) {
            int total = totalTime.get(carsNumber.get(i)) - fees[0];
            if (total < 0) {  //기본시간 내인 경우
                answer[i] = fees[1];
            } else {  //추가 요금을 계산해야 하는 경우
                answer[i] = fees[1] + (int) Math.ceil((double) total / fees[2]) * fees[3];
            }
        }
        
        return answer;
    }
    
    
    private static int convertSec(String time) {
        String[] number = time.split(":");
        return Integer.parseInt(number[0]) * 60 + Integer.parseInt(number[1]);
    }
}
