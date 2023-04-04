import java.util.HashSet;
import java.util.Set;

public class OptimalPartitionofString {
    public int partitionString(String s) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                count++;
                set.clear();
            }
            set.add(s.charAt(i));
        }
        return count + (set.isEmpty() ? 0 : 1);
    }

    // https://leetcode.com/problems/optimal-partition-of-string/solutions/3376693/image-explanation-3-approaches-o-1-space-c-java-python/
    public class Solution {
        public int partitionString(String s) {
            int i = 0, ans = 1, flag = 0;
            while(i < s.length()) {
                int val = s.charAt(i) - 'a';
                if ((flag & (1 << val)) != 0) {
                    flag = 0;
                    ans++;
                }
                flag = flag | (1 << val);
                i++;
            }
            return ans;
        }
    }
}
