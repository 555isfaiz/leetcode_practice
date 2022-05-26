// 191
public class Numberof1Bits {
    public int hammingWeight(int n) {
        int val = 0;
        while (n != 0) {
            if ((n & 1) == 1) val++;
            n = n >>> 1;
        }
        return val;
    }

    public static void main(String[] args) {
        Numberof1Bits n = new Numberof1Bits();
        System.out.println(n.hammingWeight(-3));
    }
}
