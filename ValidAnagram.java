import java.util.HashMap;
import java.util.Map;

// 242
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> characters = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (characters.containsKey(c)) characters.put(c, characters.get(c) + 1);
            else characters.put(c, 1);
        }
        for (char c : t.toCharArray()) {
            if (characters.containsKey(c)) {
                var count = characters.get(c) - 1;
                if (count == 0) characters.remove(c);
                else characters.put(c, characters.get(c) - 1);
            }
            else characters.put(c, 1);
        }
        return characters.isEmpty();
    }

    // https://leetcode.com/problems/valid-anagram/discuss/66484/Accepted-Java-O(n)-solution-in-5-lines
    public boolean isAnagramBetter(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }
}
