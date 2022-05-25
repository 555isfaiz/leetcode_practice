import java.util.Arrays;

// 354
public class RussianDollEnvelopes {
    int val_ = 1;
    int walk(int[][] envelopes, int[] used, int current) {
        if (used[current] != 0) return used[current];
        int max = 1;
        for (int i = current + 1; i < envelopes.length; i++) {
            if (envelopes[i][0] <= envelopes[current][0] || envelopes[i][1] <= envelopes[current][1]) continue;

            int t = walk(envelopes, used, i);
            max = Math.max(max, t + 1);
        }

        used[current] = Math.max(max, used[current]);
        return max;
    }

    public int maxEnvelopes(int[][] envelopes) {
        int[] used = new int[envelopes.length];
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] < b[0]) return -1;
            else if (a[0] > b[0]) return 1;
            else return Integer.compare(a[1], b[1]);
        });

        for (int i = 0; i < envelopes.length; i++) {
            int v = walk(envelopes, used, i);
            val_ = Math.max(v, val_);
        }
        return val_;
    }

    public int maxEnvelopesBetter(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if(arr1[0] == arr2[0])
                return arr2[1] - arr1[1];
            else
                return arr1[0] - arr2[0];
        });
        int dp[] = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if(index < 0)
                index = -(index + 1);
            dp[index] = envelope[1];
            if(index == len)
                len++;
        }
        return len;
    }

    public static void main(String[] args) {
        RussianDollEnvelopes r = new RussianDollEnvelopes();
        System.out.println(r.maxEnvelopesBetter(new int[][]{
//                {8,8},{19,18},{14,14},{18,8},{20,14},{14,6},{3,4},{14,1}
                {15,8},{2,20},{2,14},{4,17},{8,19},{8,9},{5,7},{11,19},{8,11},{13,11},{2,13},{11,19},{8,11},{13,11},{2,13},{11,19},{16,1},{18,13},{14,17},{18,19}
        }));
    }
}
