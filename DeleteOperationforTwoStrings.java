import java.util.*;

// 583
public class DeleteOperationforTwoStrings {
    public int minDistance(String word1, String word2) {
        List<List<Integer>> list = new ArrayList<>();
        int[] mark = new int[word1.length()];
        int max = 0;
        for (int i = 0; i < word1.length(); i++) {
            list.add(new ArrayList<>());
            int index = word2.indexOf(word1.charAt(i));
            if (index == -1) continue;
            while (index != -1) {
                list.get(i).add(index);
                index = word2.indexOf(word1.charAt(index), index + 1);
            }
        }

        int t = 0, last = -1;
        for (int i = 0; i < list.size(); i++) {
            var l = list.get(i);
            if (l.isEmpty()) continue;
            for (var j : l) {
                if (j > last) {
                    last = j;
                    break;
                }
            }
            t++;
            max = Math.max(max, t);
        }
        return word1.length() - max + word2.length() - max;
    }

    // https://leetcode.com/problems/delete-operation-for-two-strings/discuss/103214/Java-DP-Solution-(Longest-Common-Subsequence)
    public int minDistanceBetter(String word1, String word2) {
        int dp[][] = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i <= word1.length(); i++) {
            for(int j = 0; j <= word2.length(); j++) {
                if(i == 0 || j == 0) dp[i][j] = 0;
                else dp[i][j] = (word1.charAt(i-1) == word2.charAt(j-1)) ? dp[i-1][j-1] + 1
                        : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        int val =  dp[word1.length()][word2.length()];
        return word1.length() - val + word2.length() - val;
    }

    public static void main(String[] args) {
        DeleteOperationforTwoStrings d = new DeleteOperationforTwoStrings();
        System.out.println(d.minDistance("kitten",
                "sitting"));
    }
}
