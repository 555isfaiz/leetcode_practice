/**
 * Searcha2DMatrix
 */
public class Searcha2DMatrix {

  public boolean searchMatrix(int[][] matrix, int target) {
    int len = matrix.length * matrix[0].length;
    int start = 0;
    int end = len - 1;

    while (end >= start) {
      int ptr = (end + start) / 2;

      if (matrix[ptr / matrix[0].length][ptr % matrix[0].length] == target)
        return true;
      else if (matrix[ptr / matrix[0].length][ptr % matrix[0].length] >
               target) {
        end = ptr - 1;
      } else {
        start = ptr + 1;
      }
    }
    return false;
  }
}
