import java.util.Arrays;

// 948
public class BagofTokens {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int score = 0, val = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (power >= tokens[i]) {
                power -= tokens[i];
                tokens[i] = 0;
                score++;
            }
        }
        val = score;

        for (int i = tokens.length - 1; i >= 0; i--) {
            if (score <= 0) break;
            score--;
            power += tokens[i];
            for (int j = 0; j < tokens.length; j++) {
                if (tokens[j] > 0 && power >= tokens[j]) {
                    power -= tokens[j];
                    tokens[j] = 0;
                    score++;
                }
            }
            val = Math.max(val, score);
        }
        return val;
    }

    // https://leetcode.com/problems/bag-of-tokens/discuss/2564706/JAVA-oror-Easy-and-Simple-Solution-oror-98-Faster-Code-ororBeginner-Friendly
    public int bagOfTokensScoreBetter(int[] tokens, int power) {
        Arrays.sort(tokens);
        int i=0,j=tokens.length-1,score=0;
        while(i<=j)
        {
            if(power>=tokens[i])
            {
                power-=tokens[i];
                ++score;
                ++i;
            }
            else
            {
                if(score>0&&i!=j)
                {
                    power+=tokens[j];
                    --score;
                }
                --j;

            }
        }
        return Math.max(score, 0);
    }
}
