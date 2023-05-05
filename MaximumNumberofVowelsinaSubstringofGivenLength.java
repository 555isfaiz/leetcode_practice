public class MaximumNumberofVowelsinaSubstringofGivenLength {
    boolean isVowel(char c) {
        if (c == 'a' 
            || c == 'e'
            || c == 'i'
            || c == 'o'
            || c == 'u')
            return true;
        else
            return false;
    }

    public int maxVowels(String s, int k) {
        var ca = s.toCharArray();
        int res = 0, num = 0;
        for (int j = 0; j < k; j++) {
            if (isVowel(ca[j]))
                num++;
        }
        res = Math.max(res, num);
        for (int i = 0; i < s.length() - k; i++) {
            if (isVowel(ca[i]))
                num--;
            if (isVowel(ca[i + k]))
                num++;
            res = Math.max(res, num);
        }
        return res;
    }
}
