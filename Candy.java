import java.util.Arrays;

// 135
public class Candy {
  public int candy(int[] ratings) {
    int val = ratings.length;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < ratings.length; i++) {
      min = Math.min(min, ratings[i]);
    }
    return val;
  }

  // https://leetcode.com/problems/candy/discuss/42794/Simple-O(n)-Java-solution-with-comments
  public int candyBetter(int[] ratings) {
    int candies[] = new int[ratings.length];
    Arrays.fill(candies, 1); // Give each child 1 candy

    for (int i = 1; i < candies.length;
         i++) { // Scan from left to right, to make sure right higher rated
                // child gets 1 more candy than left lower rated child
      if (ratings[i] > ratings[i - 1])
        candies[i] = (candies[i - 1] + 1);
    }

    for (int i = candies.length - 2; i >= 0;
         i--) { // Scan from right to left, to make sure left higher rated child
                // gets 1 more candy than right lower rated child
      if (ratings[i] > ratings[i + 1])
        candies[i] = Math.max(candies[i], (candies[i + 1] + 1));
    }

    int sum = 0;
    for (int candy : candies)
      sum += candy;
    return sum;
  }
}
