import java.util.ArrayList;
import java.util.List;

public class Combinations {
  public void helper(List<List<Integer>> list, List<Integer> l, int start,
                     int n, int k) {
    if (l.size() == k) {
      list.add(new ArrayList<>(l));
      return;
    }

    if (start > n)
      return;

    for (int i = start; i <= n; i++) {
      l.add(i);
      helper(list, l, i + 1, n, k);
      l.remove(l.size() - 1);
    }
  }

  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> list = new ArrayList<>();
    helper(list, new ArrayList<>(), 1, n, k);
    return list;
  }

  public static void main(String[] args) {
    Combinations c = new Combinations();
    System.out.println(c.combine(2, 4));
  }
}
