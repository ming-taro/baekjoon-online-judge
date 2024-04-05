class Solution {
    public int solution(String[] board) {
        int countOfO = calcCount('O', board);
        int countOfX = calcCount('X', board);
        
        if (countOfO < countOfX
            || countOfO > countOfX + 1) {   //O가 선공이므로 항상 X와 같거나 1만큼 크다
            return 0;
        }
        
        int winOfO = calcWin('O', board);
        int winOfX = calcWin('X', board);
        
        if (winOfO > winOfX && countOfO == countOfX + 1
           || winOfO < winOfX && countOfO == countOfX
           || winOfO + winOfX == 0) {
            return 1;
        }
        
        return 0;
    }
    
    private static int calcCount(char mark, String[] board) {
        int count = 0;
        
        for (String row: board) {
            count += row.chars()
                .filter(c -> c == mark)
                .count();
        }
        
        return count;
    }
    
    private static int calcWin(char mark, String[] board) {
        int winCount = 0;
        
        for (int i = 0; i < 3; i++) {
            int rowCount = 0;
            int colCount = 0;
            
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == mark) { //가로
                    rowCount++;
                }
                if (board[j].charAt(i) == mark) { //세로
                    colCount++;
                }
            }
            
            if (rowCount == 3) {
                winCount++;
            }
            if (colCount == 3) {
                winCount++;
            }
        }
        
        if (board[0].charAt(0) == mark && board[1].charAt(1) == mark && board[2].charAt(2) == mark) {
            winCount++;
        }
        if (board[0].charAt(2) == mark && board[1].charAt(1) == mark && board[2].charAt(0) == mark) {
            winCount++;
        }
        
        return winCount;
    }
}