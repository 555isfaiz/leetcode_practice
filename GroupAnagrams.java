import java.util.*;

// 49
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (var s : strs) {
            char[] c = new char[s.length()];
            s.getChars(0, s.length(), c, 0);
            Arrays.sort(c);
            List<String> l;
            String ss = new String(c);
            if (!m.containsKey(ss)) {
                l = new ArrayList<>();
                m.put(ss, l);
            } else {
                l = m.get(ss);
            }
            l.add(s);
        }
        return new ArrayList<>(m.values());
    }

    public static void main(String[] args) {
        GroupAnagrams g = new GroupAnagrams();
        System.out.println(g.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }
}
