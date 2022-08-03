import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// 729
public class MyCalendarI {
    class MyCalendar {
        Map<Integer, Integer> events = new HashMap<>();

        public MyCalendar() {}

        public boolean book(int start, int end) {
            for (var e : events.entrySet()) {
                if (e.getKey() >= start && e.getKey() < end) return false;
                if (e.getValue() > start && e.getValue() <= end) return false;
                if (start >= e.getKey() && start < e.getValue()) return false;
                if (end > e.getKey() && end <= e.getValue()) return false;
            }
            events.put(start, end);
            return true;
        }
    }

    // https://leetcode.com/problems/my-calendar-i/discuss/109475/JavaC%2B%2B-Clean-Code-with-Explanation
    class MyCalendarBetter {

        public MyCalendarBetter() {}

        TreeMap<Integer, Integer> books = new TreeMap<>();

        public boolean book(int s, int e) {
            java.util.Map.Entry<Integer, Integer> floor = books.floorEntry(s), ceiling = books.ceilingEntry(s);
            if (floor != null && s < floor.getValue()) return false; // (s, e) start within floor
            if (ceiling != null && ceiling.getKey() < e) return false; // ceiling start within (s, e)
            books.put(s, e);
            return true;
        }
    }
}
