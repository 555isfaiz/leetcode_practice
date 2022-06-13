import java.util.ArrayList;
import java.util.List;

// 120
public class Triangle {
    int val = Integer.MAX_VALUE;
    void walk(List<List<Integer>> triangle, int level, int index, int vall) {
        if (vall > val) return;
        if (level == triangle.size() - 1) {
            val = Math.min(vall + triangle.get(level).get(index), val);
            return;
        }
        walk(triangle, level + 1, index, vall + triangle.get(level).get(index));
        walk(triangle, level + 1, index + 1, vall + triangle.get(level).get(index));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = 1; i < triangle.size(); i++) {
            var lm = triangle.get(i - 1);
            var l = triangle.get(i);
            for (int j = 0; j < l.size(); j++) {
                if (j == 0) l.set(j, l.get(j) + lm.get(0));
                else if (j == l.size() - 1) l.set(j, l.get(j) + lm.get(lm.size() - 1));
                else {
                    var t = Math.min(l.get(j) + lm.get(j), l.get(j) + lm.get(j - 1));
                    l.set(j, t);
                }
            }
            if (i == triangle.size() - 1) l.sort(Integer::compare);
        }
        return triangle.get(triangle.size() - 1).get(0);
    }

    // https://leetcode.com/problems/triangle/discuss/38724/7-lines-neat-Java-Solution
    public int minimumTotalBetter(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size()+1];
        for(int i=triangle.size()-1;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);
            }
        }
        return A[0];
    }

    public static void main(String[] args) {
        Triangle t = new Triangle();
        List<Integer> l = new ArrayList<>();
        List<Integer> ll = new ArrayList<>();
        List<Integer> lll = new ArrayList<>();
        List<Integer> llll = new ArrayList<>();
        List<List<Integer>> a = new ArrayList<>();
        l.add(2);
        ll.add(3);ll.add(4);
        lll.add(6);lll.add(5);lll.add(7);
        llll.add(4);llll.add(1);llll.add(8);llll.add(3);
        a.add(l);a.add(ll);a.add(lll);a.add(llll);
        System.out.println(t.minimumTotal(a));
    }
}
