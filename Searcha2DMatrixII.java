import java.util.Arrays;

// 240
public class Searcha2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        int xmax = matrix.length - 1, ymax = matrix[0].length - 1;
        return search(matrix, 0, xmax, 0, ymax, target);
    }

    boolean search(int[][] matrix, int xmin, int xmax, int ymin, int ymax, int target) {
        int x, y;
        if (xmax >= xmin && ymax >= ymin && xmax < matrix.length && ymax < matrix[0].length) {
            if (xmax == xmin && ymax == ymin) return matrix[xmax][ymax] == target;
            x = (xmax + xmin) / 2; y = (ymax + ymin) / 2;
            if (matrix[x][y] == target) return true;
            else {
                if (matrix[x][y] > target) {
                    return search(matrix, xmin, x, ymin, y, target)
                            ||search(matrix, xmin, x, y + 1, ymax, target)
                            || search(matrix, x + 1, xmax, ymin, y, target);
                } else {
                    return search(matrix, x + 1, xmax, y + 1, ymax, target)
                            ||search(matrix, xmin, x, y + 1, ymax, target)
                            || search(matrix, x + 1, xmax, ymin, y, target);
                }
            }
        }
        return false;
    }

    // https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/66147/*Java*-an-easy-to-understand-divide-and-conquer-method
    public boolean searchMatrixBetter(int[][] matrix, int target) {
        int m = matrix.length;
        if(m<1) return false;
        int n = matrix[0].length;

        return searchMatrix(matrix, new int[]{0,0}, new int[]{m-1, n-1}, target);
    }

    private boolean searchMatrix(int[][] matrix, int[] upperLeft, int[] lowerRight, int target) {
        if(upperLeft[0]>lowerRight[0] || upperLeft[1]>lowerRight[1]
                || lowerRight[0]>=matrix.length || lowerRight[1]>=matrix[0].length)
            return false;
        if(lowerRight[0]-upperLeft[0]==0 && lowerRight[1]-upperLeft[1]==0)
            return matrix[upperLeft[0]][upperLeft[1]] == target;
        int rowMid = (upperLeft[0] + lowerRight[0]) >> 1;
        int colMid = (upperLeft[1] + lowerRight[1]) >> 1;
        int diff = matrix[rowMid][colMid] - target;
        if(diff > 0) {
            return searchMatrix(matrix, upperLeft, new int[]{rowMid, colMid}, target)
                    || searchMatrix(matrix, new int[]{upperLeft[0],colMid+1}, new int[]{rowMid, lowerRight[1]}, target)
                    || searchMatrix(matrix, new int[]{rowMid+1,upperLeft[1]}, new int[]{lowerRight[0], colMid}, target);
        }
        else if(diff < 0) {
            return searchMatrix(matrix, new int[]{upperLeft[0], colMid+1}, new int[]{rowMid, lowerRight[1]}, target)
                    || searchMatrix(matrix, new int[]{rowMid+1, upperLeft[1]}, new int[]{lowerRight[0], colMid}, target)
                    || searchMatrix(matrix, new int[]{rowMid+1, colMid+1}, lowerRight, target);
        }
        else return true;
    }

    public static void main(String[] args) {
        Searcha2DMatrixII s = new Searcha2DMatrixII();
        System.out.println(s.searchMatrix(new int[][] {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        }, 18));
    }
}
