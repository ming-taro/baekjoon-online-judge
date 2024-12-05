import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int current = timeToInt(pos);
        
        for (String command: commands) {
            if (current >= timeToInt(op_start) && current <= timeToInt(op_end)) {
                current = timeToInt(op_end);
            }
            
            if (command.equals("next")) {
                current = (current + 10) > timeToInt(video_len) ? timeToInt(video_len) : (current + 10);
            } else {
                current = (current - 10) < 0 ? 0 : (current - 10);
            }
        }
            
        if (current >= timeToInt(op_start) && current <= timeToInt(op_end)) {
            current = timeToInt(op_end);
        }
        
        return timeToString(current);
    }
    
    private static int timeToInt(String time) {
        String[] result = time.split(":");
        return Integer.parseInt(result[0]) * 60 + Integer.parseInt(result[1]);
    }
    
    private static String timeToString(int time) {
        return String.format("%02d", (time / 60)) + ":" + String.format("%02d", (time % 60));
    }
}
/*
prev = 10초전
next = 10초후
오프닝 스킵 = 현재 위치가 오프닝구간이면 끝 위치로 이동
*/