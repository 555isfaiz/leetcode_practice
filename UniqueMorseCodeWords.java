import java.util.HashSet;
import java.util.Set;

// 804
public class UniqueMorseCodeWords {
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> code = new HashSet<>();
        String[] codes = new String[]{
                ".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."
        };
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++)
                sb.append(codes[word.charAt(i) - 97]);
            code.add(sb.toString());
        }
        return code.size();
    }
}
