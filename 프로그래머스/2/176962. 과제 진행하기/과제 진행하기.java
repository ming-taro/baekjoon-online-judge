import java.util.*;

class Solution {
    static class Task implements Comparable<Task> {
        String name;
        int startTime;
        int playTime;
        
        public Task(String name, int startTime, int playTime) {
            this.name = name;
            this.startTime = startTime;
            this.playTime = playTime;
        }
        
        public Task(String name, int playTime) {
            this.name = name;
            this.playTime = playTime;
        }
        
        @Override
        public int compareTo(Task task) {
            return this.startTime - task.startTime;
        }
        
        @Override
        public String toString() {
            return this.name + "/" + playTime;
        }
    }
    
    public String[] solution(String[][] plans) {
        List<String> result = new ArrayList<>();
        List<Task> taskPlan = new ArrayList<>();
        
        for (String[] plan: plans) {
            taskPlan.add(new Task(plan[0], calcTime(plan[1]), Integer.parseInt(plan[2])));
        }
        Collections.sort(taskPlan);
        
        int planCount = plans.length;
        String[] answer = new String[planCount];
        
        int[] startTime = new int[planCount];
        for (int i = 0; i < planCount; i++) {
            startTime[i] = taskPlan.get(i).startTime;
        }
        
        Stack<Task> restTask = new Stack<>();
        
        for (int i = 0; i < planCount - 1; i++) {
            Task task = taskPlan.get(i);
            int nextStartTime = taskPlan.get(i + 1).startTime;
            int endTime = task.startTime + task.playTime; //현재 과제의 끝나는 시각
            
            if (endTime <= nextStartTime) {  //현재 작업을 시간안에 끝낼 수 있음
                result.add(task.name);
            } else {                         //다음 작업 전까지 과제를 수행함
                restTask.add(new Task(task.name, endTime - nextStartTime)); //남은 시간 저장
                continue;
            }
            
            int restTime = nextStartTime - endTime;
                
            while (!restTask.isEmpty()) {
                Task current = restTask.pop();
                if (current.playTime <= restTime) {
                    result.add(current.name); //전에 작업중이던 과제 완료
                    restTime -= current.playTime;
                } else {
                    restTask.add(new Task(current.name, current.playTime - restTime));
                    break;
                }
            }
        }

        int index = 0;
        for (String name: result) {  //제시간에 끝낸 과제
            answer[index++] = name;
        }
        answer[index++] = taskPlan.get(planCount - 1).name; //마지막 과제
        
        while (!restTask.isEmpty()) {
            answer[index++] = restTask.pop().name; //남아있는 과제는 최근 순서부터
        }
        
        return answer;
    }
    
    private static int calcTask(int current, String task) {
        return current + Integer.parseInt(task);
    }
    
    private static int calcTime(String taskTime) {
        String[] time = taskTime.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}

/*
새로운 과제 시작 -> 기존 과제 중단 -> 새 과제 끝나면 가장 최근 멈춘 과제부터 이어서

*/