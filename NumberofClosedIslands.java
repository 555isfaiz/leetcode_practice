import java.util.LinkedList;

public class NumberofClosedIslands {
    public int closedIsland(int[][] grid) {
        LinkedList<int[]> q = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    boolean closed = true;
                    q.add(new int[] {i,j});
                    grid[i][j] = 2;
                    while (!q.isEmpty()) {
                        int size = q.size();
                        for (int k = 0; k < size; k++) {
                            var coord = q.poll();
                            if (coord[0] == 0 
                                || coord[0] == grid.length - 1 
                                || coord[1] == 0
                                || coord[1] == grid[0].length - 1)
                                closed = false;
                            
                            if (coord[0] != 0 && grid[coord[0] - 1][coord[1]] == 0) {
                                q.add(new int[] {coord[0] - 1, coord[1]});
                                grid[coord[0] - 1][coord[1]] = 2;
                            }
                            if (coord[0] != grid.length - 1 && grid[coord[0] + 1][coord[1]] == 0) {
                                q.add(new int[] {coord[0] + 1, coord[1]});
                                grid[coord[0] + 1][coord[1]] = 2;
                            }
                            if (coord[1] != 0 && grid[coord[0]][coord[1] - 1] == 0) {
                                q.add(new int[] {coord[0], coord[1] - 1});
                                grid[coord[0]][coord[1] - 1] = 2;
                            }
                            if (coord[1] != grid[0].length - 1 && grid[coord[0]][coord[1] + 1] == 0) {
                                q.add(new int[] {coord[0], coord[1] + 1});
                                grid[coord[0]][coord[1] + 1] = 2;
                            }
                        }
                    }

                    if (closed) res++;
                }
            }
        }
        return res;
    }

    // https://leetcode.com/problems/number-of-closed-islands/solutions/3384770/image-explanation-clean-generalized-code-c-java-python/
    class Solution {
        public void dfs(int i, int j, int[][] grid) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 0)
                return;

            grid[i][j] = 1;
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            for(int k=0;k<4;k++){
                int nx = i + dx[k];
                int ny = j + dy[k];
                dfs(nx, ny, grid);
            }
        }
        
        public int closedIsland(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if((i*j==0 || i==m-1 || j==n-1) && (grid[i][j]==0))
                        dfs(i, j, grid);
                }
            }
            
            int count = 0;
            for (int i = 1; i < m-1; i++) {
                for (int j = 1; j < n-1; j++) {
                    if (grid[i][j] == 0) {
                        dfs(i, j, grid);
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
