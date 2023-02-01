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

    public static void main(String[] args) {
        GreatestCommonDivisorofStrings g = new GreatestCommonDivisorofStrings();
        System.out.println(g.gcdOfStrings("ABCABC", "ABC"));
    }
}
