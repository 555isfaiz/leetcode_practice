public class GreatestCommonDivisorofStrings {
    boolean check(String s, String d) {
        for (int i = 0; i < s.length(); i += d.length()) {
            if (i + d.length() > s.length())
                return false;
            if (!s.substring(i, i + d.length()).equals(d))
                return false;
        }
        return true;
    }

    public String gcdOfStrings(String str1, String str2) {
        String toCheck, longer;
        if (str1.startsWith(str2)) {
            toCheck = str2;
            longer = str1;
        } else if (str2.startsWith(str1)) {
            toCheck = str1;
            longer = str2;
        } else {
            return "";
        }

        if (check(longer, toCheck)) return toCheck;

        for (int i = toCheck.length() / 2; i >= 1; i--) {
            String d = toCheck.substring(0, i);
            if (check(toCheck, d) && check(longer, d))
                return d;
        }
        return "";
    }

    // https://leetcode.com/problems/greatest-common-divisor-of-strings/solutions/3124997/super-easy-solution-fully-explained-c-python3-java/
    public String gcdOfStringsBetter(String str1, String str2) {
        // Check if concatenated strings are equal or not, if not return ""
        if (!(str1 + str2).equals(str2 + str1))
            return "";
        // If strings are equal than return the substring from 0 to gcd of size(str1), size(str2)
        int gcd = gcd(str1.length(), str2.length());
        return str1.substring(0, gcd);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        GreatestCommonDivisorofStrings g = new GreatestCommonDivisorofStrings();
        System.out.println(g.gcdOfStrings("ABCABC", "ABC"));
    }
}
