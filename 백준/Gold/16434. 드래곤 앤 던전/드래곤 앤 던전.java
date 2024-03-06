import java.io.*;
import java.util.*;

class Room {
    long t;
    long a;
    long h;

    public Room(long t, long a, long h) {  //방 타입, 공격력, 체력
        this.t = t;
        this.a = a;
        this.h = h;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(input.nextToken());
        long H = Integer.parseInt(input.nextToken());  //초기 공격력
        Room[] rooms = new Room[N];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(reader.readLine());
            rooms[i] = new Room(
                    Integer.parseInt(input.nextToken()),   //t = 1: 몬스터방, t = 2: 포션방
                    Integer.parseInt(input.nextToken()),
                    Integer.parseInt(input.nextToken())
            );
        }

        long[] hp = new long[N];

        for (int index = 0; index < N; index++) {
            if (rooms[index].t == 1) {  //몬스터방
                long attackCount = rooms[index].h % H == 0 ? rooms[index].h / H : rooms[index].h / H + 1;
                hp[index] = (attackCount - 1) * rooms[index].a * (-1);  //용사가 받은 피해 = (공격횟수 - 1) * 몬스터 공격력
            } else {                    //포션방
                H += rooms[index].a;    //공격력++, 체력++
                hp[index] = rooms[index].h;
            }
        }

        long maxHp = 1;
        long currentHp = 1;

        for (int i = 0; i < N; i++) {
            currentHp = Math.min((currentHp + hp[i]), maxHp);
            if (currentHp <= 0) {
                maxHp += currentHp * (-1) + 1;
                currentHp = 1;
            }
        }

        System.out.println(maxHp);
    }
}