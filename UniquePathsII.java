// 63
public class UniquePathsII {
    int path = 0;
    int dfs(int[][] obstacleGrid, int[][] visited, int x, int y) {
        if (x >= obstacleGrid.length || x < 0 || y >= obstacleGrid[0].length || y < 0)
            return 0;

        if (obstacleGrid[x][y] == 1)
            return 0;

        if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1)
            return 1;

        if (visited[x][y] != 0)
            return visited[x][y];

        int p1 = dfs(obstacleGrid, visited, x + 1, y);
        int p2 = dfs(obstacleGrid, visited, x, y + 1);
        visited[x][y] = p1 + p2;

        return visited[x][y];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 1 && obstacleGrid[0].length == 1) {
            return obstacleGrid[0][0] == 0 ? 1 : 0;
        }
        int[][] visited = new int[obstacleGrid.length][obstacleGrid[0].length];
        dfs(obstacleGrid, visited, 0, 0);
        return visited[0][0];
    }

    // https://leetcode.com/problems/unique-paths-ii/discuss/23250/Short-JAVA-solution
    public int uniquePathsWithObstaclesBetter(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
}
