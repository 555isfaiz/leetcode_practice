import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 473
public class MatchstickstoSquare {
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) return false;
        Arrays.sort(matchsticks);
        Map<Long, Integer> sides = new HashMap<>();
        long length = 0;
        for (var m : matchsticks) {
            length += m;
            if (!sides.containsKey((long) m))
                sides.put((long) m, 1);
            else
                sides.put((long) m, sides.get((long) m) + 1);
        }

        if ((length % 4) != 0) return false;
        long side = length / 4;
        if (matchsticks[matchsticks.length - 1] > side) return false;


        return false;
    }
}
