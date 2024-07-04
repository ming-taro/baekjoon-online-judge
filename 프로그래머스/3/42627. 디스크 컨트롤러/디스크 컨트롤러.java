import java.util.*;

class Solution {
    private static int current = 0;
    
    public int solution(int[][] jobs) {
        int answer = 0;
        List<Integer> nodes = new ArrayList<>();

        for (int i = 0; i < jobs.length; i++) {
            nodes.add(i);
        }
        
        for (int i = 0; i < jobs.length; i++) {
            nodes.sort((o1, o2) -> {
                if (jobs[o1][0] <= current && jobs[o2][0] <= current) {
                    return jobs[o1][1] - jobs[o2][1];
                }
                if (jobs[o1][0] == jobs[o2][0]) {
                    return jobs[o1][1] - jobs[o2][1];
                }
                return jobs[o1][0] - jobs[o2][0];
            });

            int index = nodes.get(0);
            int task = jobs[index][1];          // 해당 작업이 완료되기까지 걸린 시간
            int period = jobs[index][0] + task; // 현재 시각으로부터 해당 작업이 끝나기까지 걸린 시간
            if (current > jobs[index][0]) {
                task = current - jobs[index][0] + jobs[index][1];
                period = current + jobs[index][1];
            }   
            // System.out.println("task: start - " + jobs[index][0] + ", time - " + jobs[index][1]);
            // System.out.println("걸린시간: " + task);
            answer += task;
            current = period;
            nodes.remove(0);
        }
        
        return answer / jobs.length;
    }
}
/*
작업순서대로 한다 -> 두번째 task진행 중 들어온 task가 후순위로 밀린다
작업길이가 짧은 순서대로 한다
-> 첫번째 작업이 끝나고 수행 가능한 작업은 2개 중 무조건 1택
-> 힙 정렬 기준: 현재 시작시점과 가장 가까운 것 중 작업 길이가 짧은 것

[[5, 10], [6, 8], [14, 2], [11, 5], [100, 7]]

[5, 10] -> 15초 (10)
[14, 2] -> 17초 (3)
[11, 5] -> 22초 (11)
[6, 8]  -> 30초 (24)
[100, 7]        (7)
54 / 5 = 10

[[0, 3], [4, 4], [5, 3], [7, 1]]
[0, 3] 3초 (3)
[4, 4] 4초 (4)
[5, 3] 7초 (3)
[7, 1] 8초 (1)
*/