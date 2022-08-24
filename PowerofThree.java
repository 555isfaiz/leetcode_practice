// 326
public class PowerofThree {
    public boolean isPowerOfThree(int n) {
        if (n == 0) return false;
        while (n % 3 == 0) n = n / 3;
        return n == 1;
    }

    // https://leetcode.com/problems/power-of-three/discuss/77856/1-line-java-solution-without-loop-recursion
    public boolean isPowerOfThreeBetter(int n) {
        // 1162261467 is 3^19,  3^20 is bigger than int
        return ( n>0 &&  1162261467%n==0);
    }
}
