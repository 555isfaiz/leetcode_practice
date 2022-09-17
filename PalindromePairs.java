import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 336
public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            var w = words[i];
            if (w.isEmpty()) {
                map.put(w, i);
                continue;
            }

            StringBuilder sb = new StringBuilder();
            var carr = w.toCharArray();
            for (int j = carr.length - 1; j >= 0; j--) {
                sb.append(carr[j]);
            }

            if (map.containsKey(sb.toString())) {
                List<Integer> ind = new ArrayList<>();
                ind.add(map.get(sb.toString()));
                ind.add(i);
                res.add(ind);
            } else {
                map.put(sb.toString(), i);
            }
        }
        return res;
    }
}
