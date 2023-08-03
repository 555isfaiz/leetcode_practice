import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsofaPhoneNumber {
  public void helper(List<String> l, char[][] mapping, String digits,
                     StringBuilder sb, int index) {
    if (index >= digits.length()) {
      l.add(sb.toString());
      return;
    }

    int num = digits.charAt(index) - '0';
    for (int i = 0; i < 4; i++) {
      if (mapping[num][i] == 0)
        continue;
      sb.append(mapping[num][i]);
      helper(l, mapping, digits, sb, index + 1);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<>();
    if (digits.isEmpty()) {
      return res;
    }

    char[][] mappings = new char[10][4];
    mappings[2][0] = 'a';
    mappings[2][1] = 'b';
    mappings[2][2] = 'c';
    mappings[3][0] = 'd';
    mappings[3][1] = 'e';
    mappings[3][2] = 'f';
    mappings[4][0] = 'g';
    mappings[4][1] = 'h';
    mappings[4][2] = 'i';
    mappings[5][0] = 'j';
    mappings[5][1] = 'k';
    mappings[5][2] = 'l';
    mappings[6][0] = 'm';
    mappings[6][1] = 'n';
    mappings[6][2] = 'o';
    mappings[7][0] = 'p';
    mappings[7][1] = 'q';
    mappings[7][2] = 'r';
    mappings[7][3] = 's';
    mappings[8][0] = 't';
    mappings[8][1] = 'u';
    mappings[8][2] = 'v';
    mappings[9][0] = 'w';
    mappings[9][1] = 'x';
    mappings[9][2] = 'y';
    mappings[9][3] = 'z';

    helper(res, mappings, digits, new StringBuilder(), 0);
    return res;
  }
}
