// 1641
public class CountSortedVowelStrings {
    int count = 0;

    void count(int from, int n) {
        if (n <= 0) {
            count++;
            return;
        }

        for (int i = from; i <= 5; i++) {
            count(i, n - 1);
        }
    }

    public int countVowelStrings(int n) {
        count(1, n);
        return count;
    }

    // https://leetcode.com/problems/count-sorted-vowel-strings/discuss/2027881/Vowels-count-simple-math-faster
    int a=1, e=1, i=1, o=1, u=1;
    public int countVowelStringsBetter(int n) {
        for(int j=1; j<n; j++){
            increment();
        }
        return a+e+i+o+u;
    }

    private void increment() {
        a = a+e+i+o+u;
        e = e+i+o+u;
        i = i+o+u;
        o = o+u;
    }

    public static void main(String[] args) {
        CountSortedVowelStrings c = new CountSortedVowelStrings();
        System.out.println(c.countVowelStrings(6));
    }
}
