import java.io.*;
import java.util.*;

class Room implements Comparable<Room>{
    long start;
    long end;

    public Room(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Room room) {
        return (int) (this.start - room.start);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            rooms.add(new Room(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
        }

        Collections.sort(rooms);  //시작 시간이 빠른 순서 정렬

        PriorityQueue<Long> queue = new PriorityQueue<>();

        for (Room room: rooms) {
            if (!queue.isEmpty() && queue.peek() <= room.start) {
                queue.poll();
            }
            queue.offer(room.end);
        }

        System.out.println(queue.size());
    }
}