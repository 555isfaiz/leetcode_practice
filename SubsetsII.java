import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SubsetsII
 */
public class SubsetsII {
    Set<List<Integer>> res = new HashSet<>();
    void helper(int[] nums, List<Integer> list, int n, int i) {
        if (list.size() >= n) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int j = i; j < nums.length; j++) {
            list.add(nums[j]);
            helper(nums, list, n, j + 1);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            helper(nums, l, i, 0);
        }
        return new ArrayList<>(res);
    }

    // https://leetcode.com/problems/subsets-ii/solutions/4501326/beats-100-users-c-java-python-javascript-explained/
    class Solution {
        private void f(int index, int[] nums, List<Integer> t, List<List<Integer>> ans) {
            ans.add(new ArrayList<>(t));
    
            for (int i = index; i < nums.length; i++) {
                if (i != index && nums[i] == nums[i - 1]) continue;
                t.add(nums[i]);
                f(i + 1, nums, t, ans);
                t.remove(t.size() - 1);
            }
        }
    
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> t = new ArrayList<>();
    
            Arrays.sort(nums);
            f(0, nums, t, ans);
            return ans;
        }
    }
}