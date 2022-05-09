import java.util.ArrayList;
import java.util.List;

// 456
public class OneThreeTwoPattern {
    // failed
    public boolean find132pattern(int[] nums) {
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                int min = nums[i - 1], max = nums[i + 1];
                boolean leftEnd = false, rightEnd = false;
                int m = i - 1;
                int n = i + 1;
                while (!leftEnd || !rightEnd) {
                    if (nums[m] < min && nums[m] < nums[i]) {
                        min = nums[m];
                    } else if (nums[m] >= nums[i]) {
                        leftEnd = true;
                    }

                    if (nums[n] > max && nums[n] < nums[i]) {
                        max = nums[n];
                    } else if (nums[n] >= nums[i]) {
                        rightEnd = true;
                    }

                    if (min < max)
                        return true;

                    if (m != 0)
                        m--;
                    else
                        leftEnd = true;

                    if (n != nums.length - 1)
                        n++;
                    else
                        rightEnd = true;
                }
            }
        }
        return false;
    }

    //https://leetcode.com/problems/132-pattern/discuss/2015075/Easy-Java-Solution-Beat-100-in-Time-and-93.9-in-Space
    public boolean find132patternBetter(int[] nums) {
        int second=Integer.MIN_VALUE;
        int index=nums.length; //using nums itself as the stack (bottom on the right end)
        for(int i=nums.length-1;i>=0;i--){
            if(nums[i]<second)
                return true;
            else
                while(index<nums.length && nums[i]>nums[index])
                    second=nums[index++];
            nums[--index]=nums[i];
        }
        return false;
    }

    public static void main(String[] args) {
        OneThreeTwoPattern o = new OneThreeTwoPattern();
        System.out.println(o.find132patternBetter(new int[]{1,93,95,97,100,8,6,4,2}));
    }
}
