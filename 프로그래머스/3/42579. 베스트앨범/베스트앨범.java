import java.util.*;

class Music implements Comparable<Music> {
    int index;
    int plays;
    
    public Music(int index, int plays) {
        this.index = index;
        this.plays = plays;
    }
    
    @Override
    public int compareTo(Music music) {
        return music.plays - this.plays;
    }
} 

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        Map<String, Integer> counts = new HashMap<>();
        Map<String, PriorityQueue<Music>> result = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            if (counts.containsKey(genres[i])) {
                counts.put(genres[i], counts.get(genres[i]) + plays[i]);
            } else {
                counts.put(genres[i], plays[i]);
                Queue<Music> indexs = new PriorityQueue<>();
                indexs.offer(new Music(i, plays[i]));
                result.put(genres[i], indexs);
            }
        }
        
        for ()
        
        
        return answer;
    }
}
/*
return 장르별로 가장 많이 재생된 노래 2개

노래 수록 기준에 따라 

*/