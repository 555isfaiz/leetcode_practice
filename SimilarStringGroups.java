import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimilarStringGroups {
    public int numSimilarGroups(String[] strs) {
        List<Map<Character, Set<Integer>>> groups = new ArrayList<>();
        List<Map<Character, Set<Integer>>> founds = new ArrayList<>();
        for (int ii = 0; ii < strs.length; ii++) {
            var s = strs[ii];
            var ca = s.toCharArray();
            for (int k = 0; k < groups.size(); k++) {
                var map = groups.get(k);
                int diff = 0;
                for (int i = 0; i < ca.length; i++) {
                    if (!map.get(ca[i]).contains(i)) {
                        diff++;
                    }
                    if (diff > 2)
                        break;
                }
                if (diff <= 2)
                    founds.add(map);
            }

            Map<Character, Set<Integer>> map;
            if (founds.size() > 0) {
                map = founds.get(0);
                if (founds.size() > 1) {
                    for (int kk = 1; kk < founds.size(); kk++) {
                        var m = founds.get(kk);
                        for (var e : m.entrySet()) {
                            map.get(e.getKey()).addAll(e.getValue());
                        }
                        groups.remove(m);
                    }
                }
            } else {
                map = new HashMap<>();
                groups.add(map);
            }
            for (int i = 0; i < ca.length; i++) {
                if (!map.containsKey(ca[i]))
                    map.put(ca[i], new HashSet<>());
                map.get(ca[i]).add(i);
            }
            founds.clear();
        }
        return groups.size();
    }

    // https://leetcode.com/problems/similar-string-groups/solutions/3462253/day-393-dfs-vs-bfs-vs-union-find-100-0ms-python-java-c-explained-approach/
    class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++)
                parent[i] = i;
            rank = new int[size];
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union_set(int x, int y) {
            int xset = find(x), yset = find(y);
            if (xset == yset) {
                return;
            } else if (rank[xset] < rank[yset]) {
                parent[xset] = yset;
            } else if (rank[xset] > rank[yset]) {
                parent[yset] = xset;
            } else {
                parent[yset] = xset;
                rank[xset]++;
            }
        }
    }

    class Solution {
        public boolean isSimilar(String a, String b) {
            int diff = 0;
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    diff++;
                }
            }
            return diff == 0 || diff == 2;
        }

        public int numSimilarGroups(String[] strs) {
            int n = strs.length;
            UnionFind dsu = new UnionFind(n);
            int count = n;
            // Form the required graph from the given strings array.
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isSimilar(strs[i], strs[j]) && dsu.find(i) != dsu.find(j)) {
                        count--;
                        dsu.union_set(i, j);
                    }
                }
            }

            return count;
        }
    }

    public static void main(String[] args) {
        SimilarStringGroups s = new SimilarStringGroups();
        System.out.println(s.numSimilarGroups(new String[] {"kccomwcgcs","socgcmcwkc","sgckwcmcoc","coswcmcgkc","cowkccmsgc","cosgmccwkc","sgmkwcccoc","coswmccgkc","kowcccmsgc","kgcomwcccs"}));
    }
}
