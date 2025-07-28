import java.io.*;
import java.util.*;

class Main {
    private static Map<Integer, boolean[]> direction = new HashMap<>();
    private static int[] value = { 2, 4, 8, 1 }; // 위, 오, 아, 왼
    private static int[][] info;
    private static int[][] board;
    private static int M;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        info = new int[M][N];
        board = new int[M][N];
        for (int i = 0; i < M; i++) {
            info[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        dfs(0, 0, new boolean[]{ true, true, true, true });

        Map<Integer, Integer> rooms = new HashMap<>();
        int roomNumber = 1;
        boolean[][] visited = new boolean[M][N];

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (!visited[r][c]) {
                    rooms.put(roomNumber, bfs(r, c, roomNumber++, visited));
                }
            }
        }

        System.out.println(rooms.size()); // 방 개수

        int maxRoom = 0;
        for (int num: rooms.keySet()) {
            maxRoom = Math.max(maxRoom, rooms.get(num));
        }
        System.out.println(maxRoom); // 크기가 가장 큰 방의 넓이

        System.out.println(calcMaxRoom(rooms)); // 이어진 두 방의 최대 넓이
    }

    private static int calcMaxRoom(Map<Integer, Integer> rooms) {
        int maxRoom = 0;

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (c + 1 < N && board[r][c] != board[r][c + 1]) {
                    maxRoom = Math.max(maxRoom, rooms.get(board[r][c]) + rooms.get(board[r][c + 1]));
                }
                if (r + 1 < M && board[r][c] != board[r + 1][c]) {
                    maxRoom = Math.max(maxRoom, rooms.get(board[r][c]) + rooms.get(board[r + 1][c]));
                }
            }
        }

        return maxRoom;
    }

    private static int bfs(int row, int col, int roomNumber, boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{ row, col });
        visited[row][col] = true;

        int[] dx = { -1, 0, 1, 0 }; // 위, 오, 아, 왼
        int[] dy = { 0, 1, 0, -1 };

        int count = 0;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            board[current[0]][current[1]] = roomNumber; // 방 번호로 영역 구분
            count++; // 방 크기 카운트

            for (int i = 0; i < 4; i++) {
                int nextRow = current[0] + dx[i];
                int nextCol = current[1] + dy[i];
                if (direction.get(info[current[0]][current[1]])[i]
                    && !visited[nextRow][nextCol]) { // info키값 -> 벽이 없는 방향 i
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{ nextRow, nextCol });
                }
            }
        }

        return count;
    }

    private static void dfs(int current, int total, boolean[] result) {
        if (current == 4) {
            direction.put(total, new boolean[]{ result[0], result[1], result[2], result[3]});
            return;
        };

        result[current] = false; // 벽 세우기
        dfs(current + 1, total + value[current], result); // 현재 방향으로 벽 세웠을 때 조합
        result[current] = true;
        dfs(current + 1, total, result); // 통로일 때 조합
    }
}