import java.util.Arrays;

// 869
public class ReorderedPowerof2 {
    int len(int n) {
        int len = 0;
        while (n > 0) {
            len++; n /= 10;
        }
        return len;
    }

    public boolean reorderedPowerOf2(int n) {
        int len = 0;
        int[] counts = new int[10];
        while (n > 0) {
            len++;
            counts[n % 10]++;
            n /= 10;
        }

        int t = 1;
        while (len(t) <= len) {
            int[] c = new int[10];
            int tt = t;
            while (tt > 0) {
                c[tt % 10]++;
                tt /= 10;
            }

            if (Arrays.equals(c, counts)) return true;

            t <<= 1;
        }

        return false;
    }

    // https://leetcode.com/problems/reordered-power-of-2/discuss/149843/C%2B%2BJavaPython-Straight-Forward
    public boolean reorderedPowerOf2Better(int N) {
        long c = counter(N);
        for (int i = 0; i < 32; i++)
            if (counter(1 << i) == c) return true;
        return false;
    }
    public long counter(int N) {
        long res = 0;
        for (; N > 0; N /= 10) res += (int)Math.pow(10, N % 10);
        return res;
    }

    public static void main(String[] args) {
        ReorderedPowerof2 r = new ReorderedPowerof2();
        System.out.println(r.reorderedPowerOf2(46));
    }
}
