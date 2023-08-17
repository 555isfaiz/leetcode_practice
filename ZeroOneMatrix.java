import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ZeroOneMatrix
 */
public class ZeroOneMatrix {

    public int[][] updateMatrix(int[][] mat) {
        int[][] res = new int[mat.length][mat[0].length];
        int[][] vst = new int[mat.length][mat[0].length];
        for (int i = 0; i < res.length; i++) {
            Arrays.fill(res[i], Integer.MAX_VALUE);
        }

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[] {i, j});
                    int d = 0;
                    while (q.size() > 0) {
                        int size = q.size();
                        for (int k = 0; k < size; k++) {
                            var coord = q.poll();
                            if (coord[0] < 0 || coord[0] >= mat.length || coord[1] < 0
                                    || coord[1] >= mat[0].length || d > res[coord[0]][coord[1]]
                                    || vst[coord[0]][coord[1]] == 1) {
                                continue;
                            }
                            res[coord[0]][coord[1]] = d;
                            vst[coord[0]][coord[1]] = 1;
                            if (d > 0 && mat[coord[0]][coord[1]] == 0)
                                continue;
                            q.add(new int[] {coord[0] - 1, coord[1]});
                            q.add(new int[] {coord[0], coord[1] - 1});
                            q.add(new int[] {coord[0] + 1, coord[1]});
                            q.add(new int[] {coord[0], coord[1] + 1});
                        }
                        d++;
                    }
                    for (int l = 0; l < vst.length; i++) {
                        Arrays.fill(vst[l], 0);
                    }
                }
            }
        }
        return res;
    }

    // https://leetcode.com/problems/01-matrix/solutions/3920110/94-87-multi-source-bfs-queue/
    class Solution {
        public int[][] updateMatrix(int[][] mat) {
            if (mat == null || mat.length == 0 || mat[0].length == 0)
                return new int[0][0];

            int m = mat.length, n = mat[0].length;
            Queue<int[]> queue = new LinkedList<>();
            int MAX_VALUE = m * n;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 0) {
                        queue.offer(new int[] {i, j});
                    } else {
                        mat[i][j] = MAX_VALUE;
                    }
                }
            }

            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                for (int[] dir : directions) {
                    int r = cell[0] + dir[0], c = cell[1] + dir[1];
                    if (r >= 0 && r < m && c >= 0 && c < n
                            && mat[r][c] > mat[cell[0]][cell[1]] + 1) {
                        queue.offer(new int[] {r, c});
                        mat[r][c] = mat[cell[0]][cell[1]] + 1;
                    }
                }
            }

            return mat;
        }
    }

    public static void main(String[] args) {
        ZeroOneMatrix z = new ZeroOneMatrix();
        z.updateMatrix(new int[][] {
                {1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0,
                        1, 1},
                {1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0,
                        0, 1},
                {1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1,
                        1, 1},
                {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0,
                        0, 1},
                {0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1,
                        0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0,
                        1, 0},
                {1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1,
                        1, 1},
                {1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0,
                        0, 1},
                {0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1,
                        0, 1},
                {1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0,
                        1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1,
                        1, 0},
                {1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1,
                        1, 1},
                {0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0,
                        0, 1},
                {0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1,
                        1, 1},
                {1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1,
                        1, 1},
                {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0,
                        0, 0},
                {1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1,
                        0, 1},
                {1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1,
                        1, 0},
                {1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1,
                        1, 1},
                {1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1,
                        1, 0},
                {1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0,
                        1, 1},
                {0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1,
                        0, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1,
                        1, 1},
                {1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1,
                        1, 1},
                {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1,
                        1, 0},
                {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1,
                        1, 1},
                {1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1,
                        1, 0},
                {1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1,
                        1, 1},
                {1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1,
                        1, 1},
                {0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0,
                        1, 0}});
    }
}
