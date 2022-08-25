import java.util.HashMap;
import java.util.Map;

// 383
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] letters = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            letters[magazine.charAt(i) - 97]++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (letters[ransomNote.charAt(i) - 97]-- <= 0) return false;
        }
        return true;
    }
}
