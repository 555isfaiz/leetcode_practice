import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 1642
public class FurthestBuildingYouCanReach {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < heights.length; i++) {
            if (i >= heights.length - 1) break;
            if (heights[i] < heights[i + 1]) map.put(i, heights[i + 1] - heights[i]);
        }

        int val = 0;
        int index = 0;
        boolean broke = false;
        for (var e : map.entrySet()) {
            index = e.getKey();
            if (e.getValue() + val <= bricks) val += e.getValue();
            else if (ladders > 0) ladders--;
            else { broke = true; break; }
        }
        return broke ? index : heights.length - 1;
    }

    // https://leetcode.com/problems/furthest-building-you-can-reach/discuss/918515/JavaC%2B%2BPython-Priority-Queue
    public int furthestBuildingBetter(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < heights.length - 1; i++) {
            int d = heights[i + 1] - heights[i];
            if (d > 0)
                pq.add(d);
            if (pq.size() > ladders)
                bricks -= pq.poll();
            if (bricks < 0)
                return i;
        }
        return heights.length - 1;
    }

    public static void main(String[] args) {
        FurthestBuildingYouCanReach f = new FurthestBuildingYouCanReach();
        System.out.println(f.furthestBuilding(new int[]{14,3,19,3}, 17, 0));
    }
}
