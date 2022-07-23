import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 315
public class CountofSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int val = 0;
            if (i != nums.length - 1) {
                val = Arrays.binarySearch(nums, i + 1, nums.length, nums[i]);
                int t;
                if (val < 0) {
                    t = (-(val + 1)) - 1;
                    val = t - i;
                }
                else {
                    while (val > 0 && nums[val] == nums[i]) val--;
                    if (val < i) {val = 0; t = i;}
                    else { t = val; val = val - i; }
                }
                int insert = nums[i];
                for (int j = i; j < t; j++) {
                    nums[j] = nums[j + 1];
                }
                nums[t] = insert;
//                Arrays.sort(nums, i, nums.length);
            }
            counts.add(0, val);
        }
        return counts;
    }

    // https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation
    int[] count;
    public List<Integer> countSmallerBetter(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();

        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            indexes[i] = i;
        }
        mergesort(nums, indexes, 0, nums.length - 1);
        for(int i = 0; i < count.length; i++){
            res.add(count[i]);
        }
        return res;
    }
    private void mergesort(int[] nums, int[] indexes, int start, int end){
        if(end <= start){
            return;
        }
        int mid = (start + end) / 2;
        mergesort(nums, indexes, start, mid);
        mergesort(nums, indexes, mid + 1, end);

        merge(nums, indexes, start, end);
    }
    private void merge(int[] nums, int[] indexes, int start, int end){
        int mid = (start + end) / 2;
        int left_index = start;
        int right_index = mid+1;
        int rightcount = 0;
        int[] new_indexes = new int[end - start + 1];

        int sort_index = 0;
        while(left_index <= mid && right_index <= end){
            if(nums[indexes[right_index]] < nums[indexes[left_index]]){
                new_indexes[sort_index] = indexes[right_index];
                rightcount++;
                right_index++;
            }else{
                new_indexes[sort_index] = indexes[left_index];
                count[indexes[left_index]] += rightcount;
                left_index++;
            }
            sort_index++;
        }
        while(left_index <= mid){
            new_indexes[sort_index] = indexes[left_index];
            count[indexes[left_index]] += rightcount;
            left_index++;
            sort_index++;
        }
        while(right_index <= end){
            new_indexes[sort_index++] = indexes[right_index++];
        }
        for(int i = start; i <= end; i++){
            indexes[i] = new_indexes[i - start];
        }
    }

    public static void main(String[] args) {
        CountofSmallerNumbersAfterSelf c = new CountofSmallerNumbersAfterSelf();
        System.out.println(c.countSmaller(new int[] {
//                5, 1, 9, 1, 3, 7, 10, 33, 2, 4, 6
                5, 2, 6, 1
//                -1, -1
        }));
    }
}
