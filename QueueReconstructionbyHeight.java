import java.util.*;

// 406
public class QueueReconstructionbyHeight {
    public int[][] reconstructQueue(int[][] people) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < people.length; i++) {
            max = Math.max(max, people[i][1]);
            if (map.containsKey(people[i][1])) {
                map.get(people[i][1]).add(i);
            } else {
                PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> people[a][0] - people[b][0]);
                map.put(people[i][1], queue);
                queue.add(i);
            }
        }

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            var p = map.get(i);
            if (p == null) continue;
            int insert = 0;
            int num = 0;
            int last = 0;
            while (!p.isEmpty()) {
                int index = p.poll();
                if (!indices.isEmpty()) {
                    while (insert < indices.size() && num < i) {
                        if (people[indices.get(insert)][0] >= people[index][0]) {
                            num++;
                            last = people[indices.get(insert)][0];
                        }
                        insert++;
                    }
                }
                indices.add(insert++, index);
            }
        }

        int[][] res = new int[people.length][];
        for (int i = 0; i < res.length; i++) res[i] = people[indices.get(i)];
        return res;
    }

    // https://leetcode.com/problems/queue-reconstruction-by-height/discuss/89345/Easy-concept-with-PythonC%2B%2BJava-Solution
    public int[][] reconstructQueueBetter(int[][] people) {
        //pick up the tallest guy first
        //when insert the next tall guy, just need to insert him into kth position
        //repeat until all people are inserted into list
        Arrays.sort(people,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0]!=o2[0]?-o1[0]+o2[0]:o1[1]-o2[1];
            }
        });
        List<int[]> res = new LinkedList<>();
        for(int[] cur : people){
            res.add(cur[1],cur);
        }
        return res.toArray(new int[people.length][]);
    }

    public static void main(String[] args) {
        QueueReconstructionbyHeight q = new QueueReconstructionbyHeight();
        System.out.println(Arrays.deepToString(q.reconstructQueueBetter(new int[][]{
//                {7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}
//                {6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}
                {2,4},{3,4},{9,0},{0,6},{7,1},{6,0},{7,3},{2,5},{1,1},{8,0}
        })));
    }
}
