import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1519
public class NumberofNodesIntheSubTreeWiththeSameLabel {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        int[] res = new int[n];
        Map<Integer, List<Integer>> links = new HashMap<>();
        Map<Integer, Map<Character, Integer>> cache = new HashMap<>(); 
        for (var e : edges) {
            if (links.containsKey(e[0])) {
                links.get(e[0]).add(e[1]);
                links.putIfAbsent(e[1], new ArrayList<>());
            }
            else if (links.containsKey(e[1])) {
                links.get(e[1]).add(e[0]);
                links.putIfAbsent(e[0], new ArrayList<>());
            }
            else {
                var l = new ArrayList<Integer>();
                l.add(e[1]);
                links.put(e[0], l);
                links.put(e[1], new ArrayList<>());
            }
        }

        for (int node = 0; node < n; node++)
            res[node] = iterTree(cache, links, node, labels);

        return res;
    }

    public int iterTree(Map<Integer, Map<Character, Integer>> nums, Map<Integer, List<Integer>> links, int node, String labels) {
        if (nums.containsKey(node))
            return nums.get(node).get(labels.charAt(node));
            
        nums.putIfAbsent(node, new HashMap<>()); 
        if (links.get(node) != null && !links.get(node).isEmpty()) {
            for (var n : links.get(node)) {
                if (!nums.containsKey(n)) 
                    iterTree(nums, links, n, labels);
                
                for (var e : nums.get(n).entrySet()) {
                    if (nums.get(node).containsKey(e.getKey()))
                        nums.get(node).put(e.getKey(), nums.get(node).get(e.getKey()) + e.getValue()); 
                    else
                        nums.get(node).put(e.getKey(), e.getValue()); 
                }
            }
        }

        if (nums.get(node).containsKey(labels.charAt(node)))
            nums.get(node).put(labels.charAt(node), nums.get(node).get(labels.charAt(node)) + 1); 
        else
            nums.get(node).put(labels.charAt(node), 1); 
        return nums.get(node).get(labels.charAt(node));
    }

    // https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/solutions/3037960/java-c-javascript-dfs-explained-beats-100-concise-and-clean/
    int[] array;
    public int[] countSubTreesBetter(int n, int[][] edges, String labels) {

        array = new int[n]; //output array
    
        //creating adjacency list
        ArrayList<Integer>[] a = new ArrayList[n];
        for(int i=0;i<n;i++){
            a[i] = new ArrayList<Integer>();
        }

        for(int[] x:edges){
            a[x[0]].add(x[1]);
            a[x[1]].add(x[0]);
        }

        dfs(-1,0,a,labels);
        return array;
    }

    private int[] dfs(int prev,int curr,ArrayList<Integer>[] a,String labels)
    {
        int[] ans = new int[26];  //freq array 
        for(int x:a[curr])
        {
            if(prev!=x)
            {
                // array return by the children node
                int[] res = dfs(curr,x,a,labels);  
                // combining the frequencies of left and right subtrees into one array
                for(int i=0;i<res.length;i++)  
                    ans[i]+=res[i];
            }
        }
        // incrementing the freq of curr node label and storing in output array
        array[curr] = ++ans[labels.charAt(curr)-'a']; 
        return ans;
    }

    public static void main(String[] args) {
        NumberofNodesIntheSubTreeWiththeSameLabel n = new NumberofNodesIntheSubTreeWiththeSameLabel();
        System.out.println(n.countSubTrees(4, new int[][] {{0,2},{0,3},{1,2}}, "aeed"));
    }
}
