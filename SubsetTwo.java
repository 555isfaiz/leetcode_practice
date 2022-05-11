import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 90
public class SubsetTwo {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> s = new ArrayList<>();

    void findSubset(int[] nums, int n, int from) {
        if (n == 0) {
            var t = new ArrayList<>(s);
            t.sort(Integer::compareTo);
            if (!result.contains(t))
                result.add(t);
            return;
        }

        for (int i = from; i < nums.length; i++) {
            s.add(nums[i]);
            findSubset(nums, n - 1, i + 1);
            s.remove(s.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        for (int i = 0; i <= nums.length; i++)
            findSubset(nums, i, 0);
        return result;
    }

    // https://leetcode.com/problems/subsets-ii/discuss/169226/Java-Two-Way-of-Recursive-thinking
    public List<List<Integer>> subsetsWithDupBetter(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(res,new ArrayList<>(),nums,0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> ls, int[] nums, int pos) {
        res.add(new ArrayList<>(ls));
        for(int i=pos;i<nums.length;i++) {
            if(i>pos&&nums[i]==nums[i-1]) continue;
            ls.add(nums[i]);
            helper(res,ls,nums,i+1);
            ls.remove(ls.size()-1);
        }
    }

    public static void main(String[] args) {
        SubsetTwo s = new SubsetTwo();
        System.out.println(s.subsetsWithDup(new int[]{1, 2, 2}));
    }
}
