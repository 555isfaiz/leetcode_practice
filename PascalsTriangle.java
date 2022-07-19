import java.util.ArrayList;
import java.util.List;

// 118
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> l = new ArrayList<>();
            if (i == 0) l.add(1);
            else if (i == 1) { l.add(1); l.add(1); }
            else {
                l.add(1);
                var last = res.get(i - 1);
                for (int j = 0; j < last.size() - 1; j++) {
                    l.add(last.get(j) + last.get(j + 1));
                }
                l.add(1);
            }
            res.add(l);
        }
        return res;
    }
}
