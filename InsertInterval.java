import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int max = 0;
        for (var i : intervals) {
            if (i[1] > max)
                max = i[1];
        }

        if (max < newInterval[1])
            max = newInterval[1];

        int[] array = new int[max + 1];

        for (int i = 1; i < intervals.length + 1; i++) {
            for (int j = intervals[i - 1][0]; j <= intervals[i - 1][1]; j++)
                array[j] = i;
        }

        int start = newInterval[0];
        int end = newInterval[1];
        int val = array[start];
        while (start > 0 && array[start] != 0 && array[start - 1] == val)
            start--;

        val = array[end];
        while (end < max && array[end] != 0 && array[end + 1] == val)
            end++;

        for (int i = start; i <= end; i++)
            array[i] = Integer.MAX_VALUE;

        List<int[]> tmp = new ArrayList<>();
        int head = -1;
        for (int i = 0; i < max + 1; i++) {
            if (head == -1 && array[i] != 0) {
                head = i; val = array[i];
            } else if (head != -1 && array[i] != val) {
                tmp.add(new int[] {head, i - 1});
                if (array[i] == 0)
                    head = -1;
                else {
                    val = array[i];
                    head = i;
                }
            }
        }

        if (head != -1)
            tmp.add(new int[] {head, array.length - 1});

        int[][] res = new int[tmp.size()][2];
        int counter = 0;
        for (var i : tmp) {
            res[counter] = i;
            counter++;
        }
        return res;
    }

    // https://leetcode.com/problems/insert-interval/solutions/3056665/leetcode-the-hard-way-explained-line-by-line/
    public int[][] insertBetter(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            if (intervals[i][1] < newInterval[0]) {
                ans.add(intervals[i]);
            } else if (intervals[i][0] > newInterval[1]) {
                ans.add(newInterval);
                newInterval = intervals[i];
            } else if (intervals[i][1] >= newInterval[0] || intervals[i][0] <= newInterval[1]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }
        ans.add(newInterval);
        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        InsertInterval i = new InsertInterval();
        // i.insert(new int[][] {{1,2}, {3,5}, {6,7}, {8,10}, {12,16}}, new int[] {4,8});
        i.insert(new int[][] {{1,5}}, new int[] {0,3});
        // i.insert(new int[][] {{0,1}, {2,3}, {7,8}, {9,9}}, new int[] {19,26});
    }
}