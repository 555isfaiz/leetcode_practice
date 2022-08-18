import java.util.*;

// 1338
public class ReduceArraySizetoTheHalf {
    public int minSetSize(int[] arr) {
        int goal = (int) Math.ceil(arr.length / 2.0f);
        Map<Integer, Integer> m = new HashMap<>();
        for (var i : arr) {
            if (m.containsKey(i)) m.put(i, m.get(i) + 1);
            else m.put(i, 1);
        }
        List<Integer> l = new ArrayList<>(m.values());
        l.sort((a, b) -> b - a);
        int t = 0, i;
        for (i = 1; i < l.size(); i++) {
            t += l.get(i - 1);
            if (t >= goal) break;
        }
        return i;
    }

    public static void main(String[] args) {
        ReduceArraySizetoTheHalf r = new ReduceArraySizetoTheHalf();
        System.out.println(r.minSetSize(new int[] {
            1000, 1000, 3, 7
        }));
    }
}
