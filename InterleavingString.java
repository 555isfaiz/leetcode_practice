import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 97
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            map.put(i, new ArrayList<>());
            int index = s3.indexOf(s1.charAt(i));
            while (index != -1) {
                map.get(i).add(index);
                index = s3.indexOf(s1.charAt(i), index);
            }
        }

//        for (int i = 0)
        return false;
    }

    // https://leetcode.com/problems/interleaving-string/discuss/32078/DP-Solution-in-Java
    public boolean isInterleaveBetter(String s1, String s2, String s3) {
        if ((s1.length()+s2.length())!=s3.length()) return false;

        boolean[][] matrix = new boolean[s2.length()+1][s1.length()+1];

        matrix[0][0] = true;

        for (int i = 1; i < matrix[0].length; i++){
            matrix[0][i] = matrix[0][i-1]&&(s1.charAt(i-1)==s3.charAt(i-1));
        }

        for (int i = 1; i < matrix.length; i++){
            matrix[i][0] = matrix[i-1][0]&&(s2.charAt(i-1)==s3.charAt(i-1));
        }

        for (int i = 1; i < matrix.length; i++){
            for (int j = 1; j < matrix[0].length; j++){
                matrix[i][j] = (matrix[i-1][j]&&(s2.charAt(i-1)==s3.charAt(i+j-1)))
                        || (matrix[i][j-1]&&(s1.charAt(j-1)==s3.charAt(i+j-1)));
            }
        }

        return matrix[s2.length()][s1.length()];
    }

    public static void main(String[] args) {
        InterleavingString i = new InterleavingString();
        System.out.println(i.isInterleave(
//                "aabcc",
//                "dbbca",
//                "aadbbcbcac"
                "","b","b"
        ));
    }
}
