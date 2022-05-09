// 8
public class StringToInteger {
    public int myAtoi(String s) {
        long val = 0;
        boolean minus = false;
        boolean accept = false;
        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (!accept) {
                if (c == '-') {
                    accept = minus = true;
                    continue;
                }
                else if (c == '+') {
                    accept = true;
                    continue;
                }
                else if (c == ' ')
                    continue;
            }

            if (c < 48 || c > 57)
                break;

            val = val * 10;

            if (val < 0 || ((Long.MAX_VALUE - val) < (c - 48))) {
                val = Long.MAX_VALUE;
                break;
            }

            val += (c - 48);
            accept = true;
        }

        if (minus)
            val = Math.max(-val, Integer.MIN_VALUE);
        else
            val = Math.min(val, Integer.MAX_VALUE);
        return (int)val;
    }

    public static void main(String[] args) {
        StringToInteger s = new StringToInteger();
        System.out.println(s.myAtoi("9223372036854775808"));
    }
}
