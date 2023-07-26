public class MinimumSpeedtoArriveonTime {
    public int minSpeedOnTime(int[] dist, double hour) {
        if ((double)(dist.length - 1) > hour)
            return -1;

        int speed = 0;
        if ((double)dist.length > hour) {
            speed = (int)Math.ceilDiv((long)((dist[dist.length - 1] / (hour - (double)dist.length + 1.0)) * 100), 100);
        }
        return speed;
    }

    // https://leetcode.com/problems/minimum-speed-to-arrive-on-time/solutions/3816882/binary-search-video-explanation-in-depth-c-java-python/
    class Solution {
        public boolean isPossible(int[] dist, int speed, double hour) {
            double ans = 0;
            for (int i = 0; i < dist.length; i++) {
                double d = dist[i] * 1.0 / speed;
                if (i != dist.length - 1)
                    ans = ans + Math.ceil(d);
                else
                    ans += d;
                if (ans > hour)
                    return false;
            }
            return ans <= hour;
        }

        public int minSpeedOnTime(int[] dist, double hour) {
            int i = 1;
            int j = (int) 1e7;
            int minSpeed = -1;
            while (i <= j) {
                int mid = i + (j - i) / 2;
                if (isPossible(dist, mid, hour)) {
                    minSpeed = mid;
                    j = mid - 1;
                } else
                    i = mid + 1;
            }
            return minSpeed;
        }
    }

    public static void main(String[] args) {
        MinimumSpeedtoArriveonTime m = new MinimumSpeedtoArriveonTime();
        m.minSpeedOnTime(new int[] {1,1,100000}, 2.01);
    }
}
