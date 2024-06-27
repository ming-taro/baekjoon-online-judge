import java.util.*;

class Music implements Comparable<Music>{
    String genre;
    int index;
    int play;
    
    public Music(String genre, int play) {
        this.genre = genre;
        this.play = play;
    }
    
    public Music(int index, int play) {
        this.index = index;
        this.play = play;
    }
    
    @Override
    public int compareTo(Music music) {
        if (music.play == this.play) {
            return this.index - music.index;
        }
        return music.play - this.play;
    }
    
    @Override
    public String toString(){
        return "genre: " + genre + ", index: " + index + ", play: " + play;
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        
        List<Music> musics = new ArrayList<>();
        Map<String, Integer> indexs = new HashMap<>();
        Map<String, Queue<Music>> result = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            if (indexs.containsKey(genres[i])) {
                int index = indexs.get(genres[i]);
                musics.get(index).play += plays[i];
            } else {
                indexs.put(genres[i], musics.size());
                musics.add(new Music(genres[i], plays[i]));
                result.put(genres[i], new PriorityQueue<>());
            }
        }
        
        Collections.sort(musics);
        
        for (int i = 0; i < genres.length; i++) {
            Queue<Music> queue = result.get(genres[i]);
            queue.offer(new Music(i, plays[i]));
        }
        
        for (int i = 0; i < musics.size(); i++) {
            String genre = musics.get(i).genre;
            Queue<Music> queue = result.get(genre);
            answer.add(queue.poll().index);
            if (!queue.isEmpty()) {
                answer.add(queue.poll().index);
            }
        }
        
        System.out.println(answer);
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
/*
[입력]
장르 - 재생횟수

->장르별 재생횟수별 순위 : Map
->장르 내에서 재생횟수별 순위 : PriorityQueue
=>장르 먼저, 장르 내 1~2순위 곡

*/