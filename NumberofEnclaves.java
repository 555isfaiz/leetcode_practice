import java.util.Arrays;
import java.util.LinkedList;

public class NumberofEnclaves {
    public int numEnclaves(int[][] grid) {
        LinkedList<int[]> q = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    boolean closed = true;
                    int ncells = 0;
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
                            
                            if (coord[0] != 0 && grid[coord[0] - 1][coord[1]] == 1) {
                                q.add(new int[] {coord[0] - 1, coord[1]});
                                grid[coord[0] - 1][coord[1]] = 2;
                            }
                            if (coord[0] != grid.length - 1 && grid[coord[0] + 1][coord[1]] == 1) {
                                q.add(new int[] {coord[0] + 1, coord[1]});
                                grid[coord[0] + 1][coord[1]] = 2;
                            }
                            if (coord[1] != 0 && grid[coord[0]][coord[1] - 1] == 1) {
                                q.add(new int[] {coord[0], coord[1] - 1});
                                grid[coord[0]][coord[1] - 1] = 2;
                            }
                            if (coord[1] != grid[0].length - 1 && grid[coord[0]][coord[1] + 1] == 1) {
                                q.add(new int[] {coord[0], coord[1] + 1});
                                grid[coord[0]][coord[1] + 1] = 2;
                            }
                            ncells++;
                        }
                    }

                    if (closed) res += ncells;
                }
            }
        }
        return res;
    }

    // https://leetcode.com/problems/number-of-enclaves/solutions/3388131/python-java-c-simple-solution-easy-to-understand/
    class Solution {
        public int numEnclaves(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if ((i == 0 || j == 0 || i == m - 1 || j == n - 1) && grid[i][j] == 1) {
                        dfs(grid, i, j);
                    }
                }
            }
            return Arrays.stream(grid).mapToInt(row -> Arrays.stream(row).sum()).sum();
        }
        
        private void dfs(int[][] grid, int i, int j) {
            grid[i][j] = 0;
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] direction : directions) {
                int x = i + direction[0];
                int y = j + direction[1];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                    dfs(grid, x, y);
                }
            }
        }
    }
}
