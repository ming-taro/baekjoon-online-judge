class Solution {
    private int n;
    private int m;
    private char[][] matrix;

    public int maximalSquare(char[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;
        this.matrix = matrix;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    System.out.println(i + ", " + j);
                    answer = Math.max(answer, countSquare(i, j));
                }
            }
        }

        return answer * answer;
    }

    private int countSquare(int row, int col) {
        int len = 1;

        for (int k = 1; k < Math.min(n, m); k++) {
            if (row + k >= n || col + k >= m) { // 범위를 벗어남
                return len;
            }

            for (int i = col; i <= col + k; i++) { // 가로
                if (matrix[row + k][i] == '0') {
                    return len;
                }
            }
            for (int i = row; i <= row + k; i++) { // 세로
                if (matrix[i][col + k] == '0') {
                    return len;
                }
            }
            len++;
        }
        return len;
    }
}