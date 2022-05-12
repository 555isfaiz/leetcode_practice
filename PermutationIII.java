import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//47
public class PermutationIII {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> l = new ArrayList<>();

    void findPermutation(int[] nums, int[] markers, int n) {
        if (n <= 0) {
            list.add(new ArrayList<>(l));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (markers[i] == 1)
                continue;
            if (i > 0 && nums[i - 1] == nums[i] && markers[i - 1] == 0)
                continue;

            l.add(nums[i]);
            markers[i] = 1;
            findPermutation(nums, markers, n - 1);
            markers[i] = 0;
            l.remove(l.size() - 1);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        int[] markers = new int[nums.length];
        findPermutation(nums, markers, nums.length);
        return list;
    }

    public static void main(String[] args) {
        PermutationIII p = new PermutationIII();
        System.out.println(p.permuteUnique(new int[]{1, 1, 3}));
    }
}
