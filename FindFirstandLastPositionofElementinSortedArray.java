import java.util.Arrays;

// 34
public class FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1,-1};
        int head = 0, tail = nums.length - 1;
        while (tail >= head) {
            int mid = (head + tail) / 2;
            if (nums[mid] > target) {
                tail = mid - 1;
            } else if (nums[mid] < target) {
                head = mid + 1;
            } else {
                int left = mid, right = mid;
                while ((left >= 0 && nums[left] == target) || (right <= nums.length - 1 && nums[right] == target)) {
                    if (left >= 0 && nums[left] == target) result[0] = left;
                    if (right <= nums.length - 1 && nums[right] == target) result[1] = right;
                    left--;
                    right++;
                }
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindFirstandLastPositionofElementinSortedArray f = new FindFirstandLastPositionofElementinSortedArray();
        System.out.println(Arrays.toString(f.searchRange(new int[]{2,2}, 2)));
    }
}
