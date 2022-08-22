// 342
public class PowerofFour {
    public boolean isPowerOfFour(int n) {
        if (n == 1) return true;
        long m = 0b100;
        for (int i = 0; i <= 14; i++) {
            if (m == n) return true;
            m <<= 2;
        }
        return false;
    }

    // https://leetcode.com/problems/power-of-four/discuss/80457/Java-1-line-(cheating-for-the-purpose-of-not-using-loops)
    public boolean isPowerOfFourBetter(int num) {
        return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
        //0x55555555 is to get rid of those power of 2 but not power of 4
        //so that the single 1 bit always appears at the odd position
    }

    public static void main(String[] args) {
        PowerofFour p = new PowerofFour();
        System.out.println(p.isPowerOfFour(1073741824));
    }
}
