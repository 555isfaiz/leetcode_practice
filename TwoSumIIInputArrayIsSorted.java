import java.util.Arrays;

//167
public class TwoSumIIInputArrayIsSorted {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int val = Arrays.binarySearch(numbers, i + 1, numbers.length, target - numbers[i]);
            if (val >= 0) {
                res[0] = i + 1;
                res[1] = val + 1;
                break;
            }
        }
        return res;
    }

    // https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/discuss/51282/Simple-8-line-Java-solution-with-explanation.-O(n)
    public int[] twoSumBetter(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (numbers[l] + numbers[r] != target) {
            if (numbers[l] + numbers[r] > target) r--;
            else l++;
        }
        return new int[]{l + 1, r + 1};
    }
}
