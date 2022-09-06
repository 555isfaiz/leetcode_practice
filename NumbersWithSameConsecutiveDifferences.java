import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 967
public class NumbersWithSameConsecutiveDifferences {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            fill((int)(i * Math.pow(10, n - 1)), i, n - 1, k, res);
        }

        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) arr[i] = res.get(i);
        return arr;
    }

    void fill(int val, int last_digit, int digits_left, int k, List<Integer> list) {
        if (digits_left == 0) {
            list.add(val);
            return;
        }
        if (last_digit + k <= 9 && k != 0)
            fill((int) (val + Math.pow(10, digits_left - 1) * (last_digit + k)), last_digit + k, digits_left - 1, k, list);
        if (last_digit - k >= 0)
            fill((int) (val + Math.pow(10, digits_left - 1) * (last_digit - k)), last_digit - k, digits_left - 1, k, list);
    }
}
