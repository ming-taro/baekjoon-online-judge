class Solution {
    private static int[][] dungeons;
    private static int maxDungeon;
    private static boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;
        visited = new boolean[dungeons.length];
        dfs(0, k);
        return maxDungeon;
    }
    
    private static void dfs(int dungeon, int hp) {
        maxDungeon = Math.max(maxDungeon, dungeon);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && dungeons[i][0] <= hp) {
                visited[i] = true;
                dfs(dungeon + 1, hp - dungeons[i][1]);
                visited[i] = false;
            }
        }
    }
}
/*
k = 피로도
dungeons = [최소 피로도, 소모 피로도]
각 던전은 하루에 한 번씩만 탐험할 수 있음
return 탐험할 수 있는 최대 던전 수
*/