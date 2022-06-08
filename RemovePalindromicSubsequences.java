// 1332
public class RemovePalindromicSubsequences {
    public int removePalindromeSub(String s) {

        return 0;
    }

    // https://leetcode.com/problems/remove-palindromic-subsequences/discuss/490303/JavaC%2B%2BPython-Maximum-2-Operations
    public int removePalindromeSubBetter(String s) {
        return s.isEmpty() ? 0 : (s.equals(new StringBuilder(s).reverse().toString()) ? 1:2);
    }
}
