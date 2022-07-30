import java.util.*;

// 916
public class WordSubsets {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> res = new ArrayList<>();
        Map<String, Map<Character, Integer>> map = new HashMap<>();
        for (var s : words1) {
            boolean valid = true;
            for (var ss : words2) {
                var s_chars = s.toCharArray();
                Map<Character, Integer> s_index = build(s_chars), ss_index;
                if (map.containsKey(ss)) ss_index = map.get(ss);
                else {
                    var ss_chars = ss.toCharArray();
                    ss_index = build(ss_chars);
                    map.put(ss, ss_index);
                }

                boolean tobreak = false;
                for (var e : ss_index.entrySet()) {
                    if (!s_index.containsKey(e.getKey()) || s_index.get(e.getKey()) < e.getValue()) {
                        tobreak = true;
                        break;
                    }
                }

                if (tobreak) { valid = false; break; }
            }
            if (valid) res.add(s);
        }
        return res;
    }

    Map<Character, Integer> build(char[] chars) {
        Map<Character, Integer> map = new HashMap<>();
        for (var c : chars) {
            if (map.containsKey(c)) map.put(c, map.get(c) + 1);
            else map.put(c, 1);
        }
        return map;
    }

    // exactly same idea tho...
    // https://leetcode.com/problems/word-subsets/discuss/175854/JavaC%2B%2BPython-Straight-Forward
    public List<String> wordSubsetsBetter(String[] A, String[] B) {
        int[] count = new int[26], tmp;
        int i;
        for (String b : B) {
            tmp = counter(b);
            for (i = 0; i < 26; ++i)
                count[i] = Math.max(count[i], tmp[i]);
        }
        List<String> res = new ArrayList<>();
        for (String a : A) {
            tmp = counter(a);
            for (i = 0; i < 26; ++i)
                if (tmp[i] < count[i])
                    break;
            if (i == 26) res.add(a);
        }
        return res;
    }

    int[] counter(String word) {
        int[] count = new int[26];
        for (char c : word.toCharArray()) count[c - 'a']++;
        return count;
    }
}
