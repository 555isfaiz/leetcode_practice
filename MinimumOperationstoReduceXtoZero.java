import java.util.HashMap;
import java.util.Map;

// 1658
public class MinimumOperationstoReduceXtoZero {
    public int minOperations(int[] nums, int x) {
        int left = 0, right = nums.length - 1;
        int step = 0;
        while (left <= right) {
            if (nums[left] > x && nums[right] > x) break;
            step++;
            if (nums[left] == x || nums[right] == x) return step;
            if (nums[left] > nums[right]) {
                if (nums[left] < x) { x -= nums[left]; left++; continue;}
                if (nums[right] < x) { x -= nums[right]; right--; }
            } else {
                if (nums[right] < x) { x -= nums[right]; right--; continue; }
                if (nums[left] < x) { x -= nums[left]; left++;}
            }
        }
        return -1;
    }

    // https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/discuss/935935/Java-Detailed-Explanation-O(N)-Prefix-SumMap-Longest-Target-Sub-Array
    public int minOperationsBetter(int[] nums, int x) {
        int target = -x;
        for (int num : nums) target += num;

        if (target == 0) return nums.length;  // since all elements are positive, we have to take all of them

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; ++i) {

            sum += nums[i];
            if (map.containsKey(sum - target)) {
                res = Math.max(res, i - map.get(sum - target));
            }

            // no need to check containsKey since sum is unique
            map.put(sum, i);
        }

        return res == Integer.MIN_VALUE ? -1 : nums.length - res;
    }

    public static void main(String[] args) {
        MinimumOperationstoReduceXtoZero m = new MinimumOperationstoReduceXtoZero();
        System.out.println(m.minOperations(new int[]
//                {1241,8769,9151,3211,2314,8007,3713,5835,2176,8227,5251,9229,904,1899,5513,7878,8663,3804,2685,3501,1204,9742,2578,8849,1120,4687,5902,9929,6769,8171,5150,1343,9619,3973,3273,6427,47,8701,2741,7402,1412,2223,8152,805,6726,9128,2794,7137,6725,4279,7200,5582,9583,7443,6573,7221,1423,4859,2608,3772,7437,2581,975,3893,9172,3,3113,2978,9300,6029,4958,229,4630,653,1421,5512,5392,7287,8643,4495,2640,8047,7268,3878,6010,8070,7560,8931,76,6502,5952,4871,5986,4935,3015,8263,7497,8153,384,1136},
                {1,1,4,2,3},
//        894887480));
        5));
    }
}
