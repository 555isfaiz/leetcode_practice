import java.util.LinkedList;
import java.util.Queue;

// 695
public class MaxAreaofIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int val = 0;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 1) continue;
                q.add(new int[] {i, j});
                grid[i][j] = 2;
                int area = 0;
                while (!q.isEmpty()) {
                    area++;
                    var coords = q.poll();
                    if (coords[1] + 1 < grid[0].length && grid[coords[0]][coords[1] + 1] == 1) {
                        q.add(new int[] {coords[0], coords[1] + 1});
                        grid[coords[0]][coords[1] + 1] = 2;
                    }
                    if (coords[1] - 1 >= 0 && grid[coords[0]][coords[1] - 1] == 1) {
                        q.add(new int[] {coords[0], coords[1] - 1});
                        grid[coords[0]][coords[1] - 1] = 2;
                    }
                    if (coords[0] + 1 < grid.length && grid[coords[0] + 1][coords[1]] == 1) {
                        q.add(new int[] {coords[0] + 1, coords[1]});
                        grid[coords[0] + 1][coords[1]] = 2;
                    }
                    if (coords[0] - 1 >= 0 && grid[coords[0] - 1][coords[1]] == 1) {
                        q.add(new int[] {coords[0] - 1, coords[1]});
                        grid[coords[0] - 1][coords[1]] = 2;
                    }
                }
                val = Math.max(val, area);
            }
        }
        return val;
    }

    public static void main(String[] args) {
        MaxAreaofIsland m = new MaxAreaofIsland();
        System.out.println(m.maxAreaOfIsland(new int[][] {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
        }));
    }
}
