import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

// 1329
public class SorttheMatrixDiagonally {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int x = 0, y = i; x < m && y < n; x++, y++) {
                l.add(mat[x][y]);
            }
            l.sort(Integer::compareTo);
            for (int x = 0, y = i; x < m && y < n; x++, y++) {
                mat[x][y] = l.get(x);
            }
            l.clear();
        }

        for (int i = 1; i < m; i++) {
            for (int x = i, y = 0; x < m && y < n; x++, y++) {
                l.add(mat[x][y]);
            }
            l.sort(Integer::compareTo);
            for (int x = i, y = 0; x < m && y < n; x++, y++) {
                mat[x][y] = l.get(y);
            }
            l.clear();
        }
        return mat;
    }

    // https://leetcode.com/problems/sort-the-matrix-diagonally/discuss/489749/JavaPython-Straight-Forward
    public int[][] diagonalSortBetter(int[][] A) {
        int m = A.length, n = A[0].length;
        HashMap<Integer, PriorityQueue<Integer>> d = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d.putIfAbsent(i - j, new PriorityQueue<>());
                d.get(i - j).add(A[i][j]);
            }
        }
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                A[i][j] = d.get(i - j).poll();
        return A;
    }
}
