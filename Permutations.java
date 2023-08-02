import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public void helper(List<List<Integer>> list, List<Integer> l, int[] nums) {
        if (l.size() == nums.length) {
            list.add(new ArrayList<>(l));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (l.contains(nums[i]))
                continue;
            l.add(nums[i]);
            helper(list, l, nums);
            l.remove(l.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), nums);
        return res;
    }
}
