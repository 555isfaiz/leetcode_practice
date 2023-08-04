import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * WordBreak
 */
public class WordBreak {

  private boolean helper(String s, Map<Character, PriorityQueue<String>> dict,
                         int index, int[] deadends) {
    if (index == s.length())
      return true;

    if (deadends[index] == 1)
      return false;

    char current = s.charAt(index);
    var pq = dict.get(current);
    if (pq == null)
      return false;

    for (String ss : pq) {
      if (ss.length() + index > s.length())
        continue;
      var subString = s.substring(index, index + ss.length());
      if (!subString.equals(ss))
        continue;
      var ret = helper(s, dict, index + ss.length(), deadends);
      if (ret)
        return true;
    }

    deadends[index] = 1;
    return false;
  }

  public boolean wordBreak(String s, List<String> wordDict) {
    Map<Character, PriorityQueue<String>> map = new HashMap<>();
    for (var ss : wordDict) {
      if (map.containsKey(ss.charAt(0)))
        map.get(ss.charAt(0)).add(ss);
      else {
        var pq =
            new PriorityQueue<String>((s1, s2) -> s1.length() - s2.length());
        pq.add(ss);
        map.put(ss.charAt(0), pq);
      }
    }
    int[] deadends = new int[s.length()];
    return helper(s, map, 0, deadends);
  }

  // https://leetcode.com/problems/word-break/solutions/3860456/100-dp-dfs-video-segmenting-a-string/
  class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
      int n = s.length();
      boolean[] dp = new boolean[n + 1];
      dp[0] = true;
      int max_len = 0;
      for (String word : wordDict) {
        max_len = Math.max(max_len, word.length());
      }

      for (int i = 1; i <= n; i++) {
        for (int j = i - 1; j >= Math.max(i - max_len - 1, 0); j--) {
          if (dp[j] && wordDict.contains(s.substring(j, i))) {
            dp[i] = true;
            break;
          }
        }
      }

      return dp[n];
    }
  }

  public static void main(String[] args) {
    WordBreak w = new WordBreak();
    List<String> wordDict = new ArrayList<>();
    wordDict.add("a");
    wordDict.add("aa");
    wordDict.add("aaa");
    wordDict.add("aaaa");
    wordDict.add("aaaaa");
    wordDict.add("aaaaaa");
    wordDict.add("aaaaaaa");
    wordDict.add("aaaaaaaa");
    wordDict.add("aaaaaaaaa");
    wordDict.add("aaaaaaaaaa");
    System.out.println(w.wordBreak(
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
        wordDict));
  }
}
