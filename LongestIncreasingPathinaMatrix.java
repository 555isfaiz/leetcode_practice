import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 329
public class LongestIncreasingPathinaMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        int path_ = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] visited = new int[m][n];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] != 0) continue;
                q.add(i);
                q.add(j);
                visited[i][j] = Math.max(1, visited[i][j]);
                int p = 0;
                while (!q.isEmpty()) {
                    int size = q.size();
                    p++;
                    for (int ii = 0; ii < size / 2; ii++) {
                        var x = q.poll();
                        var y = q.poll();
                        for (var d : dir) {
                            int xx = x + d[0];
                            int yy = y + d[1];
                            if (xx >= matrix.length || xx < 0 || yy >= matrix[0].length || yy < 0) continue;
                            if (visited[xx][yy] >= p + 1) continue;
                            if (matrix[xx][yy] <= matrix[x][y]) continue;
                            visited[xx][yy] = p + 1;
                            q.add(xx);
                            q.add(yy);
                        }
                    }
                }
                path_ = Math.max(path_, p);
            }
        }
        return path_;
    }

    // https://leetcode.com/problems/longest-increasing-path-in-a-matrix/discuss/78313/Java-14ms-relative-short-and-easy-to-code-solution-with-explanation.-O(mn)-time-O(mn)-space.-DFS-%2B-DP
    public int longestIncreasingPathBetter(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] cache = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int length = findSmallAround(i, j, matrix, cache, Long.MAX_VALUE);
                max = Math.max(length, max);
            }
        }
        return max;
    }
    private int findSmallAround(int i, int j, int[][] matrix, int[][] cache, long pre) {
        // if out of bond OR current cell value larger than previous cell value.
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] >= pre) {
            return 0;
        }
        // if calculated before, no need to do it again
        if (cache[i][j] > 0) {
            return cache[i][j];
        } else {
            int cur = matrix[i][j];
            int tempMax = 0;
            tempMax = Math.max(findSmallAround(i - 1, j, matrix, cache, cur), tempMax);
            tempMax = Math.max(findSmallAround(i + 1, j, matrix, cache, cur), tempMax);
            tempMax = Math.max(findSmallAround(i, j - 1, matrix, cache, cur), tempMax);
            tempMax = Math.max(findSmallAround(i, j + 1, matrix, cache, cur), tempMax);
            cache[i][j] = ++tempMax;
            return tempMax;
        }
    }

    public static void main(String[] args) {
        LongestIncreasingPathinaMatrix l = new LongestIncreasingPathinaMatrix();
        System.out.println(l.longestIncreasingPathBetter(
//                new int[][]{{0,1,2,3,4,5,6,7,8,9},{19,18,17,16,15,14,13,12,11,10},{20,21,22,23,24,25,26,27,28,29},{39,38,37,36,35,34,33,32,31,30},{40,41,42,43,44,45,46,47,48,49},{59,58,57,56,55,54,53,52,51,50},{60,61,62,63,64,65,66,67,68,69},{79,78,77,76,75,74,73,72,71,70},{80,81,82,83,84,85,86,87,88,89},{99,98,97,96,95,94,93,92,91,90},{100,101,102,103,104,105,106,107,108,109},{119,118,117,116,115,114,113,112,111,110},{120,121,122,123,124,125,126,127,128,129},{139,138,137,136,135,134,133,132,131,130},{0,0,0,0,0,0,0,0,0,0}}
                new int[][]{{9,9,4},{6,6,8},{2,1,1}}
        ));
    }
}
