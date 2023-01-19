public class SubarraySumsDivisiblebyK {
    public int subarraysDivByK(int[] nums, int k) {
        int res = 0;
        int len = nums.length;
        int[] dp = new int[len];
        int[] sup = new int[len];
        for (int i = 0; i < len; i++) {
            if (i == 0)
                dp[i] = nums[i];
            else
                dp[i] = dp[i - 1] + nums[i];
            
            if (dp[i] % k == 0) {
                res++;
                sup[i] = res;
            }
        }

        int sofar = res;
        for (int i = 0; i < len; i++) {
            if (dp[i] % k == 0 && sofar >= dp[i]) {
                res += sofar - dp[i];
            }
        }
        return res;
    }

    // https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/3070594/java-explained-in-detail-simple-fast-solution-prefix-sum-hash-table-counting/
    public int subarraysDivByKBetter(int[] nums, int k) {
        // Use counting array to record the frequency of all the prefix sum remainders.
        int[] counting = new int[k];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) nums[i] += nums[i - 1];
            // Note that the integer in 'nums' can be negative.
            // Thus, we need to adjust the negative remainder to positive remainder.
            // Below accounts for both negative and positive remainders.
            // We can also check if the remainder is negative, then add a 'k' to make the remainder positive.
            // For Example, nums = [-2,3,2], k = 5,
            // remainder for the prefix sum of [-2,1,3] are -2, 1 and 3 respectively.
            // We know that [3,2] sum to 5, which is divisible by 5.
            // After converting -2 to 3, by adding 5, it has the same remainder with prefix sum 3.
            counting[(nums[i] % k + k) % k]++;
        }

        // The result contains all the prefix sum with remainder 0,
        // as all the prefix sum with remainder of 0 is itself divisible by 'k'.
        // However, do note that the prefix sum with remainder 0 also able to form subarray sums that is divisible by 'k'
        // with one another, which will be calculated next.
        // For Example: nums = [5,5,5,5], k = 5,
        // The prefix sum of [5,10,15,20] are themselves divisible by 5, while also forming subarray sums divisible by 5
        // with 10 [5,5] - 5 [5] == 5, 15 [5,5,5] - 5 [5] == 10, etc.
        int result = counting[0];

        // The prefix sums with the same remainder can form subarray sums that is divisible by 'k' with each other.
        // For each remainder, the number of subarray that is divisible by 'k' is the number of combinations from the frequency.
        // Equation for the number of combinations of n items is n * "(n - 1) / 2".
        for (int frequency : counting)
            result += frequency * (frequency - 1) / 2;

        return result;
    }

    public static void main(String[] args) {
        SubarraySumsDivisiblebyK s = new SubarraySumsDivisiblebyK();
        s.subarraysDivByK(new int [] {4,5,0,-2,-3,1}, 5);
    }
}