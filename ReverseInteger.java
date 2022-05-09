import java.util.LinkedList;
import java.util.Queue;

// 7
public class ReverseInteger {
    public int reverse(int x) {
        boolean minus = x < 0;
        x = Math.abs(x);
        Queue<Integer> bits = new LinkedList<>();
        while (x > 0) {
            bits.add(x % 10);
            x /= 10;
        }

        int val = 0;
        while (!bits.isEmpty()) {
            if (val != 0 && Integer.MAX_VALUE / val < 10)
                return 0;

            int b = bits.poll();
            if (val == 0 && b == 0)
                continue;

            val = val * 10 + b;
        }
        return minus ? -val : val;
    }

    //https://leetcode.com/problems/reverse-integer/discuss/2020023/Java-Simple-Solution
    public int reverseBetter(int x) {
        long rev =0;
        while(x!=0){
            int remainder = x%10;   //123%10 = 3
            rev = rev*10+remainder;  // 0*10+3 =0+3 = 3
            x=x/10; // 123/10 = 12   [and this process goes on upto x becomes 0]
        }
        if(rev>=Integer.MIN_VALUE && rev<=Integer.MAX_VALUE){
            return (int)rev;
        }else{
            return 0;
        }
    }

    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
        System.out.println(r.reverse(1534236469));
    }
}
