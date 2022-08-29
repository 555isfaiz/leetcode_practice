import java.util.LinkedList;
import java.util.Queue;

// 200
public class NumberofIslands {
    public int numIslands(char[][] grid) {
        int val = 0;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != '1') continue;
                val++;
                q.add(new int[]{i, j});
                grid[i][j] = '2';
                while (!q.isEmpty()) {
                    int size = q.size();
                    for (int ii = 0; ii < size; ii++) {
                        var ij = q.poll();
                        if (ij[0] < grid.length - 1 && grid[ij[0] + 1][ij[1]] == '1') { q.add(new int[]{ij[0] + 1, ij[1]}); grid[ij[0] + 1][ij[1]] = '2'; }
                        if (ij[1] < grid[i].length - 1 && grid[ij[0]][ij[1] + 1] == '1') { q.add(new int[]{ij[0], ij[1] + 1}); grid[ij[0]][ij[1] + 1] = '2'; }
                        if (ij[0] > 0 && grid[ij[0] - 1][ij[1]] == '1') { q.add(new int[]{ij[0] - 1, ij[1]}); grid[ij[0] - 1][ij[1]] = '2'; }
                        if (ij[1] > 0 && grid[ij[0]][ij[1] - 1] == '1') { q.add(new int[]{ij[0], ij[1] - 1}); grid[ij[0]][ij[1] - 1] = '2'; }
                    }
                }
            }
        }
        return val;
    }

    // https://leetcode.com/problems/number-of-islands/discuss/56354/1D-Union-Find-Java-solution-easily-generalized-to-other-problems
    class Solution {
        int[][] distance = {{1,0},{-1,0},{0,1},{0,-1}};
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0)  {
                return 0;
            }
            UnionFind uf = new UnionFind(grid);
            int rows = grid.length;
            int cols = grid[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        for (int[] d : distance) {
                            int x = i + d[0];
                            int y = j + d[1];
                            if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '1') {
                                int id1 = i*cols+j;
                                int id2 = x*cols+y;
                                uf.union(id1, id2);
                            }
                        }
                    }
                }
            }
            return uf.count;
        }

        class UnionFind {
            int[] father;
            int m, n;
            int count = 0;
            UnionFind(char[][] grid) {
                m = grid.length;
                n = grid[0].length;
                father = new int[m*n];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (grid[i][j] == '1') {
                            int id = i * n + j;
                            father[id] = id;
                            count++;
                        }
                    }
                }
            }
            public void union(int node1, int node2) {
                int find1 = find(node1);
                int find2 = find(node2);
                if(find1 != find2) {
                    father[find1] = find2;
                    count--;
                }
            }
            public int find (int node) {
                if (father[node] == node) {
                    return node;
                }
                father[node] = find(father[node]);
                return father[node];
            }
        }
    }

    public static void main(String[] args) {
        NumberofIslands n = new NumberofIslands();
        System.out.println(n.numIslands(new char[][] {
//                {'1','1','0','0','0'},
//                {'1','1','0','0','0'},
//                {'0','0','1','0','0'},
//                {'0','0','0','1','1'}
        }));
    }
}
