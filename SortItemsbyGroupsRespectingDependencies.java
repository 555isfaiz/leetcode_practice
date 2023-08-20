import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SortItemsbyGroupsRespectingDependencies
 */
public class SortItemsbyGroupsRespectingDependencies {
    private boolean placeBefore(List<Integer> list, int i, Map<Integer, List<Integer>> groups,
            int[] group, List<List<Integer>> beforeItems, Set<Integer> road) {
        if (group[i] == -2 || (group[i] != -1 && !groups.containsKey(group[i])))
            return true;

        road.add(i);
        if (group[i] != -1) {
            for (var j : groups.get(group[i])) {
                for (var k : beforeItems.get(j)) {
                    if (groups.get(group[i]).contains(k))
                        continue;
                    if (road.contains(k))
                        return false;
                    if (!placeBefore(list, k, groups, group, beforeItems, road))
                        return false;
                }
            }
            list.addAll(groups.remove(group[i]));
        } else {
            for (var j : beforeItems.get(i)) {
                if (road.contains(j))
                    return false;
                if (!placeBefore(list, j, groups, group, beforeItems, road))
                    return false;
            }
            list.add(i);
        }
        group[i] = -2;
        road.remove(i);
        return true;
    }

    private boolean placeIngroupBefore(List<Integer> list, int i,
            int[] group, List<List<Integer>> beforeItems, Set<Integer> road) {
        if (list.contains(i))
            return true;

        road.add(i);
        List<Integer> toRemove = new ArrayList<>();
        for (var j : beforeItems.get(i)) {
            if (group[i] != group[j])
                continue;
            if (road.contains(j))
                return false;
            if (!placeIngroupBefore(list, j, group, beforeItems, road))
                return false;
            toRemove.add(j);
        }
        for (var r : toRemove)
            beforeItems.get(i).remove(r);

        list.add(i);
        road.remove(i);
        return true;
    }

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, List<Integer>> groups = new HashMap<>();
        Set<Integer> road = new HashSet<>();
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1)
                continue;

            if (!groups.containsKey(group[i]))
                groups.put(group[i], new ArrayList<>());

            if (!placeIngroupBefore(groups.get(group[i]), i, group, beforeItems, road))
                return new int[0];
        }

        for (int i = 0; i < n; i++) {
            if (!placeBefore(list, i, groups, group, beforeItems, road))
                return new int[0];
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        SortItemsbyGroupsRespectingDependencies s = new SortItemsbyGroupsRespectingDependencies();

        List<List<Integer>> beforeItems = new ArrayList<>();
        System.out.println(s.sortItems(8, 2, new int[] {-1, -1, 1, 0, 0, 1, 0, -1}, beforeItems));
    }
}
