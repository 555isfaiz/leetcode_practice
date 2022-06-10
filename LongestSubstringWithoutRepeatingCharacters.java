import java.util.HashMap;
import java.util.Map;

// 3
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int val = 0;
        int t = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (map.containsKey(c) && map.get(c) >= start) { t -= (map.get(c) - start); start = map.get(c) + 1; }
            else t++;
            map.put(c, i);
            val = Math.max(val, t);
        }
        return val;
    }

    // same idea, but I dont know why this is always faster... someone enlight me plz
    // https://leetcode.com/problems/longest-substring-without-repeating-characters/discuss/1729/11-line-simple-Java-solution-O(n)-with-explanation
    public int lengthOfLongestSubstringBetter(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters l = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(l.lengthOfLongestSubstring("bbtablud"));
    }
}
