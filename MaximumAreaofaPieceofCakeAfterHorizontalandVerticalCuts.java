import java.util.Arrays;

// 1465
public class MaximumAreaofaPieceofCakeAfterHorizontalandVerticalCuts {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int h_s = 0, w_s = 0, h_max = 0, w_max = 0;
        for (int i = 0; i < Math.max(horizontalCuts.length, verticalCuts.length); i++) {
            if (i < horizontalCuts.length) {
                h_max = Math.max(horizontalCuts[i] - h_s, h_max);
                h_s = horizontalCuts[i];
                if (i == horizontalCuts.length - 1) h_max = Math.max(h - horizontalCuts[i], h_max);
            }
            if (i < verticalCuts.length) {
                w_max = Math.max(verticalCuts[i] - w_s, w_max);
                w_s = verticalCuts[i];
                if (i == verticalCuts.length - 1) w_max = Math.max(w - verticalCuts[i], w_max);
            }
        }
        return (int)((long)h_max * w_max % 1000000007);
    }

    // https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/discuss/661644/C%2B%2BJava-Maximum-Gap-Between-Cuts
    public int maxAreaBetter(int h, int w, int[] hCuts, int[] vCuts) {
        Arrays.sort(hCuts);
        Arrays.sort(vCuts);
        int max_h = Math.max(hCuts[0], h - hCuts[hCuts.length - 1]);
        int max_v = Math.max(vCuts[0], w - vCuts[vCuts.length - 1]);
        for (int i = 0; i < hCuts.length - 1; ++i)
            max_h = Math.max(max_h, hCuts[i + 1] - hCuts[i]);
        for (int i = 0; i < vCuts.length - 1; ++i)
            max_v = Math.max(max_v, vCuts[i + 1] - vCuts[i]);
        return (int)((long)max_h * max_v % 1000000007);
    }

    public static void main(String[] args) {
        MaximumAreaofaPieceofCakeAfterHorizontalandVerticalCuts m = new MaximumAreaofaPieceofCakeAfterHorizontalandVerticalCuts();
        System.out.println(m.maxArea(
                1000000000,
                1000000000,
                new int[] {2},
                new int[] {2}));
    }
}
