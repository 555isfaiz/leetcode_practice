import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

// 820
public class ShortEncodingofWords {
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int res = 0;
        for (int i = words.length - 1; i >= 0; i--) {
            boolean found = false;
            for (int j = i + 1; j < words.length; j++) {
                if (words[j].endsWith(words[i])) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                res += words[i].length() + 1;
            }
        }
        return res;
    }

    //https://leetcode.com/problems/short-encoding-of-words/discuss/125811/C%2B%2BJavaPython-Easy-Understood-Solution-with-Explanation
    public int minimumLengthEncodingBetter(String[] words) {
        Set<String> s = new HashSet<>(Arrays.asList(words));
        for (String w : words)
            for (int i = 1; i < w.length(); ++i)
                s.remove(w.substring(i));
        int res = 0;
        for (String w : s) res += w.length() + 1;
        return res;
    }
}
