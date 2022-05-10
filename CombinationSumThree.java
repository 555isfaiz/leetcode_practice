import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 216
public class CombinationSumThree {
    int[] marker = new int[9];
    List<Integer> findCombination(int sign, int k, int n) {
        if (n <= 0 || k <= 0)
            return null;

        List<Integer> r = null;
        if (k == 1) {
            if (n <= 9 && marker[n - 1] != sign) {
                marker[n - 1] += 1;
                r = new ArrayList<>();
                r.add(n);
            }
            return r;
        }

        for (int i = 1; i <= 9; i++) {
            if (marker[i - 1] == sign)
                continue;

            marker[i - 1] += 1;
            r = findCombination(sign, k - 1, n - i);
            if (r == null || r.contains(i)) {
                marker[i - 1] -= 1;
                continue;
            }

            r.add(i);
            if (r.size() == k)
                break;
        }
        return r;
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            marker[i - 1] += 1;
            int sign = 1;
            while (true) {
                var l = findCombination(sign, k - 1, n - i);
                if (l == null || l.contains(i))
                    break;

                l.add(i);
                l.sort(Integer::compare);
                if (!list.contains(l)) {
                    list.add(l);
                } else
                    marker[l.get(0) - 1] += 1;
            }
            Arrays.fill(marker, 0);
        }
        return list;
    }

    List<List<Integer>> allComb = new ArrayList<>();
    List<Integer> comb = new ArrayList<>();

    // https://leetcode.com/problems/combination-sum-iii/discuss/2023978/Java-oror-simple-and-easy-solution-oror-beats-100
    public List<List<Integer>> combinationSum3Better(int k, int n) {
        findComb(k, n, 1);
        return allComb;
    }

    private void findComb(int k, int n, int start) {
        if(k == 0 && n == 0){
            allComb.add(new ArrayList<>(comb));
            return;
        }
        for(int i = start; i < 10; i++){
            comb.add(i);
            findComb(k - 1, n - i, i + 1);
            comb.remove(comb.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSumThree c = new CombinationSumThree();
        System.out.println(c.combinationSum3(4, 24));
    }
}
