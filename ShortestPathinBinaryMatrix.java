import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// 1091
public class ShortestPathinBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int plen = -1;
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1)
            return plen;
        int[][] dir = new int[][]{{1,1},{1,0},{0,1},{-1,1},{-1,0},{0,-1},{1,-1},{-1,-1}};
        int m = grid.length - 1, n = grid[0].length - 1;
        int[][] path = new int[m + 1][n + 1];
        path[0][0] = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((t1, t2) -> {
            int diff1 = (m - t1[0]) * (m - t1[0]) + (n - t1[1]) * (n - t1[1]);
            int diff2 = (m - t2[0]) * (m - t2[0]) + (n - t2[1]) * (n - t2[1]);
            if (diff1 == diff2)
                return Integer.compare(t1[2], t2[2]);
            else
                return Integer.compare(diff1, diff2);
        });
        pq.offer(new int[]{0,0,1});
        while (!pq.isEmpty()) {
            int size = pq.size();
            for (int i = 0; i < size; i++) {
                var ints = pq.poll();
                if (plen != -1 && ints[2] >= plen)
                    continue;

                if (ints[0] == m && ints[1] == n) {
                    plen = ints[2];
                    continue;
                }

                for (var d : dir) {
                    int x = ints[0] + d[0];
                    int y = ints[1] + d[1];
                    if (x > m || x < 0 || y > n || y < 0) continue;
                    if (grid[x][y] == 1) continue;
                    if (path[x][y] == 0 || ints[2] + 1 < path[x][y]) {
                        path[x][y] = ints[2] + 1;
                        pq.offer(new int[]{x,y,ints[2] + 1});
                    }
                }
            }
        }
        return plen;
    }

    // https://leetcode.com/problems/shortest-path-in-binary-matrix/discuss/312706/JAVA-BFS
    public int shortestPathBinaryMatrixBetter(int[][] grid) {
        int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,-1},{-1,1},{-1,-1},{1,1}};
        int m = grid.length;
        int n = grid[0].length;

        if(grid[0][0]==1 || grid[m-1][n-1]==1) {
            return -1;
        }

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});

        int ans=0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] pop = queue.remove();
                if(pop[0]==m-1 && pop[1]==n-1) {
                    return ans+1;
                }
                for (int k=0;k<8;k++) {
                    int nextX = dir[k][0]+pop[0];
                    int nextY = dir[k][1]+pop[1];

                    if(nextX>=0 && nextX<m && nextY>=0 && nextY<n && !visited[nextX][nextY] && grid[nextX][nextY]==0) {
                        queue.add(new int[]{nextX,nextY});
                        visited[nextX][nextY]=true;
                    }

                }
            }
            ans++;
        }

        return -1;
    }

    public static void main(String[] args) {
        ShortestPathinBinaryMatrix s = new ShortestPathinBinaryMatrix();
        System.out.println(s.shortestPathBinaryMatrix(new int[][]{
                {0,0,1,0,0,0,0},{0,1,0,0,0,0,1},{0,0,1,0,1,0,0},{0,0,0,1,1,1,0},{1,0,0,1,1,0,0},{1,1,1,1,1,0,1},{0,0,1,0,0,0,0}
        }));
    }
}
