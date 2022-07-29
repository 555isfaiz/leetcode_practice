import java.util.*;

// 890
public class FindandReplacePattern {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (var s : words) {
            if (s.length() != pattern.length()) continue;
            Map<Character, Character> map = new HashMap<>();
            Set<Character> set = new HashSet<>();
            boolean match = true;
            for (int i = 0; i < s.length(); i++) {
                if (map.containsKey(s.charAt(i))) {
                    if (!map.get(s.charAt(i)).equals(pattern.charAt(i))) {
                        match = false;
                        break;
                    }
                } else {
                    if (set.contains(pattern.charAt(i))) { match = false; break; }
                    map.put(s.charAt(i), pattern.charAt(i));
                    set.add(pattern.charAt(i));
                }
            }
            if (match) result.add(s);
        }
        return result;
    }

    // https://leetcode.com/problems/find-and-replace-pattern/discuss/161288/C%2B%2BJavaPython-Normalise-Word
    public List<String> findAndReplacePatternBetter(String[] words, String pattern) {
        int[] p = F(pattern);
        List<String> res = new ArrayList<String>();
        for (String w : words)
            if (Arrays.equals(F(w), p)) res.add(w);
        return res;
    }

    public int[] F(String w) {
        HashMap<Character, Integer> m = new HashMap<>();
        int n = w.length();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            m.putIfAbsent(w.charAt(i), m.size());
            res[i] = m.get(w.charAt(i));
        }
        return res;
    }
}
