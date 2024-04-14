import java.util.*;

class Point implements Comparable<Point>{
    int start;
    int end;
    
    public Point (int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public int compareTo (Point point) {
        if (this.start == point.start) {
            return this.end - point.end;
        }
        return this.start - point.start;
    }
}

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        Queue<Point> points = new PriorityQueue<>();
        
        for (int[] target: targets) {
            points.offer(new Point(target[0], target[1]));
        }
        
        Point startPoint = points.poll();
        int start = startPoint.start;
        int end = startPoint.end;
        
        for (int i = 1; i < targets.length; i++) {
            Point current = points.poll();
            
            if (current.start >= end) {  //새로운 미사일을 쏘는 시점
                start = current.start;
                end = current.end;
                answer++;
            } else if (current.end <= end) {
                end = current.end;
            }
        }
        
        return answer;
    }
}
/*
A->B(아이기스) 침공
B는 미사일을 최소로 사용해서 모든 폭격 미사일을 요격하려 함
s와 e에서는 요격할 수 없음
*/