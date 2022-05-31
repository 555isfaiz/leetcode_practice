import java.util.HashSet;
import java.util.Set;

// 1461
public class CheckIfaStringContainsAllBinaryCodesofSizeK {
    boolean val = true;
    int mask = 0;
    void check(String s, int k, int cur, int tmp) {
        if (cur < k) {
            tmp <<= 1;
            check(s, k, cur + 1, tmp);
            if (!val) return;
            tmp >>= 1;
            tmp <<= 1; tmp |= 1;
            check(s, k, cur + 1, tmp);
            if (!val) return;
            tmp >>= 1;
            return;
        }

        int slide = 0;
        for (int i = 0; i < s.length(); i++) {
            slide <<= 1;
            if (s.charAt(i) == '1') slide |= 1;
            slide &= mask;
            if (i >= k - 1 && slide == tmp) {
                val = true;
                return;
            }
        }
        val = false;
    }
    public boolean hasAllCodes(String s, int k) {
        if (s.length() < k) return false;
        for (int i = 0; i < k; i++) {
            mask <<= 1; mask |= 1;
        }
        check(s, k, 0, 0);
        return val;
    }

    // https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/discuss/660546/JavaPython-3-4-and-1-liners-clean-codes-using-set-w-brief-explanation-and-analysis.
    public boolean hasAllCodesBetter(String s, int k) {
        Set<String> seen = new HashSet<>();
        for (int i = k; i <= s.length() && seen.size() < 1 << k; ++i) {
            seen.add(s.substring(i - k, i));
        }
        return seen.size() == 1 << k;
    }

    public static void main(String[] args) {
        CheckIfaStringContainsAllBinaryCodesofSizeK c = new CheckIfaStringContainsAllBinaryCodesofSizeK();
        System.out.println(c.hasAllCodes("0110", 2));
    }
}
