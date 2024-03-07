import java.io.*;
import java.util.*;

class Position {
    int row;
    int col;
    int count;

    public Position(int row, int col, int count) {
        this.row = row;
        this.col = col;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", col=" + col +
                ", count=" + count +
                '}';
    }
}

public class Main {
    private static int[][] board;
    private static Position[][] passengers;
    private static Position taxi;
    private static int N, M, fuel;
    private static final int PERSON = 2;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(input.nextToken());
        M = Integer.parseInt(input.nextToken());
        fuel = Integer.parseInt(input.nextToken()); //초기 연료량
        board = new int[N][N];
        passengers = new Position[N][N];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        input = new StringTokenizer(reader.readLine());
        taxi = new Position(Integer.parseInt(input.nextToken()) - 1, Integer.parseInt(input.nextToken()) - 1, 0);

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(reader.readLine());

            int startRow = Integer.parseInt(input.nextToken()) - 1;
            int startCol = Integer.parseInt(input.nextToken()) - 1;

            int endRow = Integer.parseInt(input.nextToken()) - 1;
            int endCol = Integer.parseInt(input.nextToken()) - 1;

            board[startRow][startCol] = PERSON;
            passengers[startRow][startCol] = new Position(endRow, endCol, 0);
        }

        for (int i = 0; i < M; i++) {
            Position startTaxi = findPerson();
            fuel -= startTaxi.count;                 //손님을 태우기 위해 소모한 연료
            if (startTaxi.row == N || fuel <= 0) {   //태울 수 있는 손님이 X or 연료가 하나도 없는 경우
                System.out.println(-1);
                return;
            }

            Position destinationTaxi = findDestination(startTaxi, passengers[startTaxi.row][startTaxi.col]);
            if (destinationTaxi.row == -1 || destinationTaxi.count > fuel) {
                System.out.println(-1);
                return;
            }

            fuel += destinationTaxi.count;  //목적지 도착시 연료 2배 충전
            taxi = destinationTaxi;             //택시의 현재 위치 갱신
            board[startTaxi.row][startTaxi.col] = 0;
        }

        System.out.println(fuel);
    }

    private static Position findDestination(Position start, Position destination) {
        start.count = 0;  //목적지까지 길 찾기 전 count 초기화

        Queue<Position> queue = new LinkedList<>();
        queue.offer(start);

        boolean[][] visited = new boolean[N][N];
        visited[start.row][start.col] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (current.row == destination.row && current.col == destination.col) {
                return current;
            }

            for (int i = 0; i < 4; i++) {
                Position next  = new Position(current.row + dx[i], current.col + dy[i], current.count + 1);
                if (isWithinRange(next)
                        && !visited[next.row][next.col]
                        && board[next.row][next.col] != 1) {
                    queue.offer(next);
                    visited[next.row][next.col] = true;
                }
            }
        }

        return new Position(-1, -1, -1);
    }

    private static Position findPerson() {
        taxi.count = 0;
        Queue<Position> queue = new LinkedList<>();
        queue.offer(taxi);

        boolean[][] visited = new boolean[N][N];
        visited[taxi.row][taxi.col] = true;

        int minCount = Integer.MAX_VALUE;
        Position personToFind = new Position(N, N, 0);

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (board[current.row][current.col] == PERSON) {
                if (minCount == Integer.MAX_VALUE) {
                    minCount = current.count;
                    personToFind = current;
                } else if (current.count <= minCount) {
                    if (current.row < personToFind.row
                            || current.row == personToFind.row && current.col < personToFind.col) {
                        personToFind = current;
                        minCount = current.count;
                    }
                }
            }
            if (current.count > minCount) {    //현재 찾은 승객보다 먼 거리에 있는 승객은 더 이상 탐색할 필요X
                continue;
            }

            for (int i = 0; i < 4; i++) {
                Position next  = new Position(current.row + dx[i], current.col + dy[i], current.count + 1);
                if (isWithinRange(next)
                        && !visited[next.row][next.col]
                        && board[next.row][next.col] != 1) {
                    queue.offer(next);
                    visited[next.row][next.col] = true;
                }
            }
        }

        return personToFind;
    }

    private static boolean isWithinRange(Position position) {
        return position.row >= 0 && position.row < N && position.col >= 0 && position.col < N;
    }
}
/*
N*N 맵에서 M명의 승객을 태우려고 함
한 칸 이동시 연료 - 1, 목적지 도착시 + 소모 연료량 * 2
이동중 연료 = 0 -> 실패
 */