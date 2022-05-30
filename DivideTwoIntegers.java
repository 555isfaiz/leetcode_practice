// 29
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        long tmp = (long)dividend / divisor;
        if (tmp > Integer.MAX_VALUE) tmp = Integer.MAX_VALUE;
        if (tmp < Integer.MIN_VALUE) tmp = Integer.MIN_VALUE;
        return (int)tmp;
    }

    // https://leetcode.com/problems/divide-two-integers/discuss/142849/C%2B%2BJavaPython-Should-Not-Use-%22long%22-Int
    public int divideBetter(int A, int B) {
        if (A == 1 << 31 && B == -1) return (1 << 31) - 1;
        int a = Math.abs(A), b = Math.abs(B), res = 0, x = 0;
        while (a - b >= 0) {
            for (x = 0; a - (b << x << 1) >= 0; x++);
            res += 1 << x;
            a -= b << x;
        }
        return (A > 0) == (B > 0) ? res : -res;
    }
}
