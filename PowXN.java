import java.util.LinkedHashMap;
import java.util.Map;

public class PowXN {
    public static void main(String[] args) {
        var p = new PowXN();
        System.out.println(p.myPow(2, -2147483648));
    }

    public double myPow(double x, int n) {
        long nn = n;
        if (nn < 0) {
            x = 1 / x;
            nn = -nn;
        } else if (nn == 0) {
            return 1.0f;
        }

        double res = 1.0f; int max = 0;
        Map<Integer, Double> dp = new LinkedHashMap<>();
        while (nn > 0) {
            if (nn >= max && dp.containsKey(max)) {
                res *= dp.get(max);
                nn -= max;
                max *= 2;
            } else {
                int m = 0;
                for (var k : dp.keySet()) {
                    if (k > nn)
                    break;
                    if (k > m)
                    m = k;
                }
                if (m != 0) {
                    res *= dp.get(m);
                    nn -= m;
                    max += m;
                } else {
                    res *= x;
                    max++;
                    nn--;
                }
            }

            if (max <= nn)
                dp.put(max, res);
        }
        return res;
    }

    // https://leetcode.com/problems/powx-n/solutions/3807721/2-optimised-method-bit-recursion-line-by-line-explained-faster-log-n-solution/
    public class Solution {
        public double solve(double x, long n) {
            double ans = 1;
            while (n > 0) {
                if ((n & 1) == 1) { // checking if it is odd then we will multiply one extra value of x
                    ans *= x;
                }
                x *= x;
                n >>= 1;
            }
            return ans;
        }

        public double myPow(double x, int n) {
            if (x == 1) return 1;
            long longN = n;
            double ans = solve(x, Math.abs(longN));
            if (longN < 0)
                return 1 / ans;
            return ans;
        }
    }
}