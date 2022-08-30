// 48
public class RotateImage {
    public void rotate(int[][] matrix) {
        int[] tl = new int[]{0, 0};
        int[] tr = new int[]{0, matrix.length - 1};
        int[] br = new int[]{matrix.length - 1, matrix.length - 1};
        int[] bl = new int[]{matrix.length - 1, 0};
        for (int i = matrix.length; i > 0; i -= 2) {
            rotateEdge(matrix, tl, tr, br, bl);
            tl[0] += 1; tl[1] += 1;
            tr[0] += 1; tr[1] -= 1;
            br[0] -= 1; br[1] -= 1;
            bl[0] -= 1; bl[1] += 1;
        }
    }

    void rotateEdge(int[][] matrix, int[] tl, int[] tr, int[] br, int[] bl) {
        int[] ntl = new int[]{tl[0], tl[1]};
        int[] ntr = new int[]{tr[0], tr[1]};
        int[] nbr = new int[]{br[0], br[1]};
        int[] nbl = new int[]{bl[0], bl[1]};
        for (int i = 0; i < tr[1] - tl[1]; i++) {
            int t1 = matrix[ntl[0]][ntl[1]], t2 = matrix[ntr[0]][ntr[1]], t3 = matrix[nbr[0]][nbr[1]], t4 = matrix[nbl[0]][nbl[1]];
            matrix[ntl[0]][ntl[1]] = t4;
            matrix[ntr[0]][ntr[1]] = t1;
            matrix[nbr[0]][nbr[1]] = t2;
            matrix[nbl[0]][nbl[1]] = t3;
            ntl[1] += 1; ntr[0] += 1; nbr[1] -= 1; nbl[0] -= 1;
        }
    }

    // https://leetcode.com/problems/rotate-image/discuss/18879/AC-Java-in-place-solution-with-explanation-Easy-to-understand.
    public void rotateBetter(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }
}
