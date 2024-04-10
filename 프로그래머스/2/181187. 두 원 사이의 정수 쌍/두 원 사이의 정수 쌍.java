class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for (int x = 1; x <= r2; x++) {
            int maxY = (int) Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2));
            double minY = Math.sqrt(Math.pow(r1, 2) - Math.pow(x, 2));
            
            if (minY > (int) minY) {
                answer += maxY - ((int) minY + 1) + 1;
            } else {
                answer += maxY - (int) minY + 1;
            }
        }
        
        return answer * 4;
    }
}