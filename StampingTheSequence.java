import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 936
public class StampingTheSequence {
    public int[] movesToStamp(String stamp, String target) {
        Stack<Integer> s = new Stack<>();
        int index = target.indexOf(stamp);
        while (index != -1) {
            s.add(index);
            index = target.indexOf(stamp, index + 1);
        }
        if (s.isEmpty()) return new int[0];

        for (int i = 0; i < 10 * target.length(); i++) {
            target.indexOf(stamp);
        }
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(target.length() - stamp.length());
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) { res[i] = list.get(i); }
        return res;
    }

    // https://leetcode.com/problems/stamping-the-sequence/discuss/201546/12ms-Java-Solution-Beats-100
    public int[] movesToStampBetter(String stamp, String target) {
        char[] S = stamp.toCharArray();
        char[] T = target.toCharArray();
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[T.length];
        int stars = 0;

        while (stars < T.length) {
            boolean doneReplace = false;
            for (int i = 0; i <= T.length - S.length; i++) {
                if (!visited[i] && canReplace(T, i, S)) {
                    stars = doReplace(T, i, S.length, stars);
                    doneReplace = true;
                    visited[i] = true;
                    res.add(i);
                    if (stars == T.length) {
                        break;
                    }
                }
            }

            if (!doneReplace) {
                return new int[0];
            }
        }

        int[] resArray = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = res.get(res.size() - i - 1);
        }
        return resArray;
    }

    private boolean canReplace(char[] T, int p, char[] S) {
        for (int i = 0; i < S.length; i++) {
            if (T[i + p] != '*' && T[i + p] != S[i]) {
                return false;
            }
        }
        return true;
    }

    private int doReplace(char[] T, int p, int len, int count) {
        for (int i = 0; i < len; i++) {
            if (T[i + p] != '*') {
                T[i + p] = '*';
                count++;
            }
        }
        return count;
    }
}
