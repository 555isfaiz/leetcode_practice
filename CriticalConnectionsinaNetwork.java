import java.util.*;

// 1192
public class CriticalConnectionsinaNetwork {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> conn = new HashMap<>();
        for (var c : connections) {
            conn.computeIfAbsent(c.get(0), k -> new ArrayList<>()).add(c.get(1));
            conn.computeIfAbsent(c.get(1), k -> new ArrayList<>()).add(c.get(0));
        }

        Queue<Integer> q = new LinkedList<>();
        for (var c : connections) {
            int[] visited = new int[n];
            int nVisited = 0;
            conn.get(c.get(0)).remove(Integer.valueOf(c.get(1)));
            conn.get(c.get(1)).remove(Integer.valueOf(c.get(0)));
            q.add(c.get(0));
            visited[c.get(0)] = 1;
            nVisited++;
            while (!q.isEmpty()) {
                int x = q.poll();
                for (var iii : conn.get(x)) {
                    if (visited[iii] == 0) {
                        if (iii.equals(c.get(1))) {
                            nVisited = n;
                            q.clear();
                            break;
                        }
                        visited[iii] = 1;
                        nVisited++;
                        q.add(iii);
                    }
                }
            }
            if (nVisited < n) {
                result.add(c);
            }
            conn.get(c.get(0)).add(c.get(1));
            conn.get(c.get(1)).add(c.get(0));
        }
        return result;
    }

    // https://leetcode.com/problems/critical-connections-in-a-network/discuss/401340/Clean-Java-Solution-With-Explanation!!!-Great-Question!
    public List<List<Integer>> criticalConnectionsBetter(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for(List<Integer> oneConnection :connections) {
            graph[oneConnection.get(0)].add(oneConnection.get(1));
            graph[oneConnection.get(1)].add(oneConnection.get(0));
        }
        int timer[] = new int[1];
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int []timeStampAtThatNode = new int[n];
        criticalConnectionsUtil(graph, -1, 0, timer, visited, results, timeStampAtThatNode);
        return results;
    }

    public void criticalConnectionsUtil(List<Integer>[] graph, int parent, int node, int timer[], boolean[] visited, List<List<Integer>> results, int []timeStampAtThatNode) {
        visited[node] = true;
        timeStampAtThatNode[node] = timer[0]++;
        int currentTimeStamp = timeStampAtThatNode[node];

        for(int oneNeighbour : graph[node]) {
            if(oneNeighbour == parent) continue;
            if(!visited[oneNeighbour]) criticalConnectionsUtil(graph, node, oneNeighbour, timer, visited, results, timeStampAtThatNode);
            timeStampAtThatNode[node] = Math.min(timeStampAtThatNode[node], timeStampAtThatNode[oneNeighbour]);
            if(currentTimeStamp < timeStampAtThatNode[oneNeighbour]) results.add(Arrays.asList(node, oneNeighbour));
        }
    }

    public static void main(String[] args) {
        CriticalConnectionsinaNetwork c = new CriticalConnectionsinaNetwork();
        List<List<Integer>> cc = new ArrayList<>();
        List<Integer> c1 = new ArrayList<>();
        List<Integer> c2 = new ArrayList<>();
        List<Integer> c3 = new ArrayList<>();
        List<Integer> c4 = new ArrayList<>();
        List<Integer> c5 = new ArrayList<>();
        List<Integer> c6 = new ArrayList<>();
        List<Integer> c7 = new ArrayList<>();
        c1.add(0); c1.add(1);
        c2.add(1); c2.add(2);
        c3.add(0); c3.add(2);
        c4.add(3); c4.add(1);
        c5.add(3); c5.add(4);
        c6.add(4); c6.add(5);
        c7.add(3); c7.add(5);
        cc.add(c1);cc.add(c2);cc.add(c3);cc.add(c4);cc.add(c5);cc.add(c6);cc.add(c7);
        System.out.println(c.criticalConnectionsBetter(6, cc));
    }
}
