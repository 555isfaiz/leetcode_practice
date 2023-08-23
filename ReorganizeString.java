import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * ReorganizeString
 */
public class ReorganizeString {

    public String reorganizeString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (var c : s.toCharArray()) {
            map.compute(c, (k, v) -> v == null ? 1 : v + 1);
            max = Math.max(max, map.get(c));
        }

        if (max - 1 > s.length() - max)
            return "";

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Character> pq = new PriorityQueue<>((c1, c2) -> map.get(c2) - map.get(c1));
        pq.addAll(map.keySet());
        while (sb.length() < s.length()) {
            Character c1 = pq.poll(), c2 = pq.poll();
            if (c1 != null) {
                sb.append(c1);
                map.compute(c1, (k, v) -> --v);
                if (map.get(c1) > 0)
                    pq.add(c1);
            }

            if (c2 != null) {
                sb.append(c2);
                map.compute(c2, (k, v) -> --v);
                if (map.get(c2) > 0)
                    pq.add(c2);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReorganizeString r = new ReorganizeString();
        System.out.println(r.reorganizeString("aab"));
    }
}
