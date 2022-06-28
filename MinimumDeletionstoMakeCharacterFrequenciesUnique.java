import java.util.*;

// 1647
public class MinimumDeletionstoMakeCharacterFrequenciesUnique {
    public int minDeletions(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            counts[c - 97]++;
        }

        int max = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 0) continue;
            max = Math.max(max, counts[i]);
            if (map.containsKey(counts[i])) map.put(counts[i], map.get(counts[i]) + 1);
            else map.put(counts[i], 1);
        }

        int val = 0;
        int toAdd = 0;
        for (int i = max; i > 0; i--) {
            if (map.containsKey(i)) {
                val += map.get(i) - 1 + toAdd;
                toAdd += map.get(i) - 1;
            } else if (toAdd != 0) {
                val += --toAdd;
            }
        }
        return val;
    }

    // https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/discuss/927654/C%2B%2BJavaPython3-Simple-time-O(n)-space-O(1)-a-small-array-is-all-you-need
    public int minDeletionsBetter(String s) {
        int freq[] = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        Arrays.sort(freq);
        int keep = freq[25], prev = keep;
        for (int i = 24; i >= 0 && freq[i] != 0 && prev != 0; i--) {
            prev = Math.min(freq[i], prev - 1);
            keep += prev;
        }
        return s.length() - keep;
    }

    public static void main(String[] args) {
        MinimumDeletionstoMakeCharacterFrequenciesUnique m = new MinimumDeletionstoMakeCharacterFrequenciesUnique();
        System.out.println(m.minDeletions("accdcdadddbaadbc"));
    }
}
