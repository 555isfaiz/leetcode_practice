import java.util.Arrays;
import java.util.HashMap;

// 128
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int val = 0, t = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                if (nums[i] - nums[i - 1] > 1) {
                    val = Math.max(val, t);
                    t = 1;
                } else if (nums[i] - nums[i - 1] == 1) t++;
            }
        }
        val = Math.max(val, t);
        return val;
    }

    public int longestConsecutiveRealBigON(int[] num) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);

                // keep track of the max length
                res = Math.max(res, sum);

                // extend the length to the boundary(s)
                // of the sequence
                // will do nothing if n has no neighbors
                map.put(n - left, sum);
                map.put(n + right, sum);
            }
            else {
                // duplicates
                continue;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence l = new LongestConsecutiveSequence();
        System.out.println(l.longestConsecutive(new int[] {
//                0,3,7,2,5,8,4,6,0,1
                1,2,0,1
        }));
    }
}
