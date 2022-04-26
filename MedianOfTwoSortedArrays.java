// 4
public class MedianOfTwoSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0)
            return len2 % 2 == 0 ? (nums2[len2/2] + nums2[len2/2 - 1]) / 2.0f : nums2[len2/2];
        if (len2 == 0)
            return len1 % 2 == 0 ? (nums1[len1/2] + nums1[len1/2 - 1]) / 2.0f : nums1[len1/2];

        int cursor1 = 0, cursor2 = 0;
        int middle = -1, before_middle = -1;
        for (int i = 0; i <= (len1 + len2) / 2; i++) {
            int n;
            if (cursor2 >= len2 || (cursor1 < len1 && nums1[cursor1] <= nums2[cursor2]))
                n = nums1[cursor1++];
            else
                n = nums2[cursor2++];
            if (i == 0)
                before_middle = n;
            else
                before_middle = middle;

            middle = n;
        }
        return (len1 + len2) % 2 == 0 ? (before_middle + middle) / 2.0f : middle;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3,5,7,9,11};
        int[] nums2 = new int[]{2,6};
        int[] nums0 = new int[0];
        System.out.println(findMedianSortedArrays(nums2,nums2));
    }
}
