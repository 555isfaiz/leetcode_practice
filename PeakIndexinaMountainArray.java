public class PeakIndexinaMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int ptr = (left + right) / 2;
            if (ptr == 0)
                ptr++;
            else if (ptr == arr.length - 1)
                ptr--;
            if (arr[ptr] > arr[ptr - 1] && arr[ptr] < arr[ptr + 1]) {
                left = ptr + 1;
            } else if (arr[ptr] < arr[ptr - 1] && arr[ptr] > arr[ptr + 1]) {
                right = ptr - 1;
            } else {
                return ptr;
            }
        }
        return -1;
    }

    // https://leetcode.com/problems/peak-index-in-a-mountain-array/solutions/3813115/java-fast-100-best-memory-usage-easy-to-understand/
    class Solution {
	    public int peakIndexInMountainArray(int[] arr) {
            int i = arr.length / 2;
            int arrLen = i;
            while (true) {
                if (arr[i]>arr[i+1]&&arr[i]>arr[i-1])break;
                if (arr[i]<arr[i+1]){
                    arrLen /= 2;
                    i+=arrLen;
                }
                else {
                    i-=arrLen/2;
                }
            }
            return i;
        }
    }

    public static void main(String[] args) {
        PeakIndexinaMountainArray p = new PeakIndexinaMountainArray();
        System.out.println(p.peakIndexInMountainArray(new int[] {3,5,3,2,0}));
    }
}
