import java.util.*;

class Truck {
    int time;
    int weight;
    
    public Truck(int time, int weight)  {
        this.time = time;
        this.weight = weight;
    }
}

class Bridge {
    int time;
    int weight;
    int count;
    
    public Bridge(int time, int weight, int count) {
        this.time = time;
        this.weight = weight;
        this.count = count;
    }
}

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> queue = new LinkedList<>();
        
        int index = 1;
        Bridge bridge = new Bridge(2, truck_weights[0], 1);
        queue.offer(new Truck(1, truck_weights[0]));
        
        while (index < truck_weights.length) {
            Truck current = queue.peek();
            if (bridge.time - current.time == bridge_length) { // 트럭 도착
                queue.poll();
                bridge.weight -= current.weight;
                bridge.count--;
            }
            
            if (bridge.count < bridge_length
                && truck_weights[index] + bridge.weight <= weight) {
                queue.offer(new Truck(bridge.time, truck_weights[index])); // 트럭 추가
                bridge.weight += truck_weights[index];
                bridge.count++;
                index++;
            }
            
            bridge.time++; 
        }
        
        while (queue.size() > 1) {
            queue.poll();
        }
        
        return queue.poll().time + bridge_length;
    }
}
/*
return 모든 트럭이 다리를 건너기 위해 걸리는 시간

- 트럭이 최대 bridge_length개 만큼, 총 weight 이하 까지
- 트럭은 정해진 순서대로 건너야 함
- 트럭은 한 대씩 올라갈 수 있음(사이에 최소 1초의 시간이 걸림)

1. 다리위의 트럭 무게와 개수 기억
2. 트럭이 한 번 올라옴(1초) - 건넘(2초) - 도착(3초)
-> [7, 4, 5, 6]
   [(7, 1)] -> 1초
   [(7, 1)] -> 2초
   (7, 1) // [] ->       3초(건너는 데 2초가 소요 -> 3 - 1 = 2이므로 탈출)
   (7, 1) // [(4, 3)] -> 3초(트럭은 한번에 한 대만 올라올 수 있음)
   (7, 1) // [(4, 3), (5, 4)] -> 4초(현재 2대, 총 9kg)
   (7, 1) (4, 3) // [(5, 4)]  -> 5초(4번 트럭이 5 - 3 = 2로 탈출, 6번은 무게 제한으로X)
   (7, 1) (4, 3) (5, 4) // [(6, 6)] -> 6초(5번 트럭이 6 - 4)
   
*/