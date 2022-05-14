import java.util.*;

// 743
public class NetworkDelayTime {
    int curPtr = 0;
    int curTime = Integer.MAX_VALUE;
    Map<Integer, List<int[]>> route = new HashMap<>();

    void walk(int target, int time, int[] markers) {
        var ptr = route.get(curPtr);
        if (ptr == null) return;
        for (var r : ptr) {
            if (r[1] == target) {
                curTime = Math.min(curTime, r[2] + time);
                continue;
            } else if (markers[r[1]] == 1) {
                continue;
            }
            markers[r[1]] = 1;
            curPtr = r[1];
            walk(target, time + r[2], markers);
            markers[r[1]] = 0;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        int time = -1;

        for (int[] ints : times) {
            List<int[]> l;
            if (route.containsKey(ints[0])) {
                l = route.get(ints[0]);
            } else {
                l = new ArrayList<>();
                route.put(ints[0], l);
            }
            l.add(ints);
        }

        int[] marker = new int[n+1];
        for (int i = 1; i <= n; i++) {
            if (i == k)
                continue;
            curPtr = k;
            marker[i] = 1;
            walk(i, 0, marker);
            marker[i] = 0;

            if (curTime == Integer.MAX_VALUE)
                return -1;
            time = Math.max(curTime, time);
            curTime = Integer.MAX_VALUE;
        }

        return time;
    }

    // https://leetcode.com/problems/network-delay-time/discuss/210698/Java-Djikstrabfs-Concise-and-very-easy-to-understand
    public int networkDelayTimeBetter(int[][] times, int N, int K) {
        Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
        for(int[] time : times){
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }

        //distance, node into pq
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        pq.add(new int[]{0, K});

        boolean[] visited = new boolean[N+1];
        int res = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.remove();
            int curNode = cur[1];
            int curDist = cur[0];
            if(visited[curNode]) continue;
            visited[curNode] = true;
            res = curDist;
            N--;
            if(map.containsKey(curNode)){
                for(int next : map.get(curNode).keySet()){
                    pq.add(new int[]{curDist + map.get(curNode).get(next), next});
                }
            }
        }
        return N == 0 ? res : -1;
    }

    public static void main(String[] args) {
        NetworkDelayTime n = new NetworkDelayTime();
        System.out.println(n.networkDelayTimeBetter(new int[][]{{1,2,1},{2,3,1},{1,3,7}}, 3, 1));
    }
}
