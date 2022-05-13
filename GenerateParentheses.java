import java.util.ArrayList;
import java.util.List;

// 22
public class GenerateParentheses {
    List<String> result = new ArrayList<>();
    StringBuilder s = new StringBuilder("()");

    void doGenerate(int n, int from) {
        if (n == 0) {
            result.add(s.toString());
            return;
        }

        for (int i = from + 1; i <= s.length(); i++) {
            s.insert(i, "()");
            doGenerate(n - 1, i);
            s.delete(i, i + 2);
        }
    }

    public List<String> generateParenthesis(int n) {
        doGenerate(n - 1, 0);
        return result;
    }

    public static void main(String[] args) {
        GenerateParentheses g = new GenerateParentheses();
        System.out.println(g.generateParenthesis(3));
    }
}
