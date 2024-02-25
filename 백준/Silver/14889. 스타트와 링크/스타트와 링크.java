import java.io.*;
import java.util.*;

public class Main {
    private static int[][] abilities;
    private static int N;
    private static int minDifference = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        abilities = new int[N][N];

        for (int i = 0; i < N; i++) {
            abilities[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        List<Integer> team = new ArrayList<>();
        trace(team, 0);

        System.out.println(minDifference);
    }

    private static void trace(List<Integer> startTeam, int number) {
        if (startTeam.size() == N / 2) {  //팀 완성 -> 능력치를 계산해 두 팀의 능력치 차의 최소를 구함
            minDifference = Math.min(minDifference, calcAbilityDifference(startTeam));
            return;
        }
        if (number == N - 1) {  // 더 이상 팀이 구성되지X
            return;
        }

        for (int i = number; i < N; i++) {
            startTeam.add(i);
            trace(startTeam, i + 1);
            startTeam.remove(startTeam.size() - 1);
        }
    }

    private static int calcAbilityDifference(List<Integer> startTeam) {
        List<Integer> linkTeam = new ArrayList<>();

        for (int i = 0, j = 0; i < N; i++) {
            if (j < N / 2 && startTeam.get(j) == i) {
                j++;
            } else {
                linkTeam.add(i);
            }
        }

        return Math.abs(calcAbility(startTeam) - calcAbility(linkTeam));
    }

    private static int calcAbility(List<Integer> members) {
        int totalAbility = 0;

        for (int i = 0; i < N / 2 - 1; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                totalAbility += abilities[members.get(i)][members.get(j)] + abilities[members.get(j)][members.get(i)];
            }
        }

        return totalAbility;
    }
}
/*
->축구: 평일 오후, 의무 참석X
->총 N명(짝수)
->N/2명씩 스타트, 링크팀으로 나눠야 함
->능력치 테이블: i, j 사람이 같은 팀일 때 팀능력치 += S[i][j] + S[j][i]
=>두 팀의 능력치 차이 최솟값
 */