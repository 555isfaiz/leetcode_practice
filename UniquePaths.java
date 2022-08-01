// 62
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        long total = m + n - 2;
        if (m > n) m = n - 1;
        else m --;
        long res = 1;
        for (int i = m + 1; i <= total; i++) {
            res *= i;
            res /= i - m;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        UniquePaths u = new UniquePaths();
        System.out.println(u.uniquePaths(23, 12));
    }
}
