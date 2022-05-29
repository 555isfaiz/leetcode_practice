// 318
public class MaximumProductofWordLengths {
    boolean overlap(String a, String b, int ai, int bi, int[] letters) {
        if (letters[ai] == 0) {
            int val = 0;
            for (int i = 0; i < a.length(); i++) {
                val |= (1 << (a.charAt(i) - 97));
            }
            letters[ai] = val;
        }

        if (letters[bi] == 0) {
            int val = 0;
            for (int i = 0; i < b.length(); i++) {
                val |= (1 << (b.charAt(i) - 97));
            }
            letters[bi] = val;
        }

        return (letters[ai] & letters[bi]) != 0;
    }
    public int maxProduct(String[] words) {
        int value = 0;
        int[] letters = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int ii = i + 1; ii < words.length; ii++) {
                if (words[i].length() * words[ii].length() <= value) continue;
                if (!overlap(words[i], words[ii], i, ii, letters)) {
                    value = words[i].length() * words[ii].length();
                }
            }
        }
        return value;
    }
}
