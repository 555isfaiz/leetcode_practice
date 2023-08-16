import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/**
 * SlidingWindowMaximum
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1)
            return nums;
        int[] res = new int[nums.length - k + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> nums[i2] - nums[i1]);

        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                pq.add(i);
            } else {
                res[i - k] = nums[pq.peek()];
                while (pq.peek() < i - k + 1)
                    pq.poll();
                pq.add(i);
            }
        }
        res[res.length - 1] = nums[pq.peek()];
        return res;
    }

    // https://leetcode.com/problems/sliding-window-maximum/solutions/3915747/video-ex-amazon-explains-a-solution-with-python-javascript-java-and-c/
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            List<Integer> res = new ArrayList<>();
            int left = 0;
            int right = 0;
            Deque<Integer> q = new ArrayDeque<>();

            while (right < nums.length) {
                while (!q.isEmpty() && nums[right] > nums[q.peekLast()]) {
                    q.pollLast();
                }
                q.offerLast(right);

                if (left > q.peekFirst()) {
                    q.pollFirst();
                }

                if (right + 1 >= k) {
                    res.add(nums[q.peekFirst()]);
                    left++;
                }
                right++;
            }

            return res.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    public static void main(String[] args) {
        SlidingWindowMaximum s = new SlidingWindowMaximum();
        System.out.println(s.maxSlidingWindow(new int[] {7, 2, 4}, 2));
    }
}
