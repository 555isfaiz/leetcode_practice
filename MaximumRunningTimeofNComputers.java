import java.util.Arrays;

public class MaximumRunningTimeofNComputers {
    public long maxRunTime(int n, int[] batteries) {
        Arrays.sort(batteries);
        int[] pcs = new int[n];
        for (int i = 0; i < n; i++) {
            pcs[i] = batteries[batteries.length - n + i];
        }

        long time_total = 0;
        for (int i = batteries.length - n; i >= 0; i--) {
            time_total += pcs[0];
            for (int j = 0; j < pcs.length; j++) {
                pcs[j] -= pcs[0];
            }
            pcs[0] = batteries[i];
            Arrays.sort(pcs);
        }
        return time_total;
    }

    // https://leetcode.com/problems/maximum-running-time-of-n-computers/solutions/3821752/beats-100-video-java-c-python/
    class Solution {
        public long maxRunTime(int n, int[] batteries) {
            long sumPower = 0;
            for (int power : batteries)
                sumPower += power;
            long left = 1, right = sumPower / n;
            
            while (left < right){
                long time = (left+right+1) / 2;
                if (check(batteries, n, time))
                    left = time;
                else
                    right = time - 1;
            }
            return left;
        }
        
        public boolean check(int [] B, int n, long time){
            long sum = 0;
            for(int battery: B){
                sum+=Math.min(battery, time);
            }
            return (sum>=(long)time*n);
        }
    }
    
    public static void main(String[] args) {
        long j = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            j = i * 2 - j;
        }
        System.out.println(j);
    }
}
