// 1423
public class MaximumPointsYouCanObtainfromCards {
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0, min_sum, window_sum;
        int window = cardPoints.length - k - 1;
        if (k == cardPoints.length) {
            for (int i = 0; i <= cardPoints.length - 1; i++) {
                sum += cardPoints[i];
            }
            return sum;
        }

        for (int i = 0; i <= window; i++) {
            sum += cardPoints[i];
        }

        min_sum = sum;
        window_sum = sum;
        int start = 0;

        while (window != cardPoints.length - 1) {
            window_sum -= cardPoints[start];
            start++; window++;
            window_sum += cardPoints[window];
            min_sum = Math.min(window_sum, min_sum);
            sum += cardPoints[window];
        }

        return sum - min_sum;
    }

    // https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/discuss/598111/Java-dp-solution(explanation-with-picture)
    public int maxScoreDP(int[] cardPoints, int k) {
        int[] dp = new int[k + 1];
        //   this dp array stores the total points when taking i cards from left.
        //   since we could take 0 - k cards from left ,the length of the dp array would be k+1.

        for (int i = cardPoints.length - 1; i >= cardPoints.length - k; i--) {
            dp[0] += cardPoints[i];
        }
        int max_points = dp[0];

        for (int i = 1; i < k + 1; i++) {
            dp[i] = dp[i - 1] + cardPoints[i - 1] - cardPoints[cardPoints.length - k + i - 1];
            max_points = Math.max(max_points, dp[i]);
        }

        return max_points;
    }

    public static void main(String[] args) {
        MaximumPointsYouCanObtainfromCards m = new MaximumPointsYouCanObtainfromCards();
        System.out.println(m.maxScore(new int[] {1, 1000, 1}, 1));
    }
}
