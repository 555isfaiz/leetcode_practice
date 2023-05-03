import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindtheDifferenceofTwoArrays {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        Set<Integer> common = new HashSet<>();
        int i = 0, j = 0;
        for (; i < nums1.length || j < nums2.length; i++, j++) {
            if (i < nums1.length) {
                if (!common.contains(nums1[i])) {
                    if (s2.contains(nums1[i])) {
                        common.add(nums1[i]);
                        s2.remove(nums1[i]);
                    } else
                        s1.add(nums1[i]);
                }
            }
            if (j < nums2.length) {
                if (!common.contains(nums2[j])) {
                    if (s1.contains(nums2[j])) {
                        common.add(nums2[j]);
                        s1.remove(nums2[j]);
                    } else
                        s2.add(nums2[j]);
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>(s1));
        res.add(new ArrayList<>(s2));
        return res;
    }
}