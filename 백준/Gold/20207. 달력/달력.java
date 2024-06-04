import java.io.*;
import java.util.*;

class Plan implements Comparable<Plan> {
    int start;
    int end;

    public Plan(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Plan plan) {
        if (this.start == plan.start) {
            return plan.end - this.end;
        }
        return this.start - plan.start;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Plan> plans = new ArrayList<>();

        int N = Integer.parseInt(reader.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(input.nextToken());
            int end = Integer.parseInt(input.nextToken());
            plans.add(new Plan(start, end));
        }

        Collections.sort(plans); // 총 일정 -> 시작 일이 빠른 순 + 가장 늦게 끝나는 순

        int total = 0;
        int start = -10;
        int maxEndDate = -10;
        Queue<Integer> endDate = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            Plan current = plans.get(i);

            if (current.start > maxEndDate + 1) {
                total += (maxEndDate - start + 1) * endDate.size(); // 하나의 그룹이 끝남

                start = current.start;     // 현재 일정 블록의 start, end 범위 갱신
                maxEndDate = current.end;

                endDate = new PriorityQueue<>(); // 새로운 endQueue 시작
                endDate.add(current.end);
                continue;
            }

            if (!endDate.isEmpty() && endDate.peek() + 1 <= current.start) { // 앞선 일정과 같은 줄에 넣을 수 있는 일정 -> 해당 일정의 endDate 갱신 필요
                endDate.poll();
            }
            endDate.add(current.end);
            maxEndDate = Math.max(maxEndDate, current.end); // 가장 늦게 끝나는 일정의 날짜 갱신
        }

        total += (maxEndDate - start + 1) * endDate.size(); // 마지막 그룹
        System.out.println(total);
    }
}