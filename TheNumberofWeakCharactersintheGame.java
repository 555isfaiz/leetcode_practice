import java.util.*;

// 1996
public class TheNumberofWeakCharactersintheGame {
    public int numberOfWeakCharacters(int[][] properties) {
        int val = 0;
        List<int[]> arr = new ArrayList<>(Arrays.asList(properties));

        arr.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            else return Integer.compare(b[1], a[1]);
        });

        int max = arr.get(arr.size() - 1)[1];
        for (int i = arr.size() - 1; i >= 0; i--) {
            if (arr.get(i)[1] < max)
                val++;
            else
                max = arr.get(i)[1];
        }
        return val;
    }

    public static void main(String[] args) {
        TheNumberofWeakCharactersintheGame t = new TheNumberofWeakCharactersintheGame();
        System.out.println(t.numberOfWeakCharacters(new int[][]{
                {1,1},{2,1},{2,2},{1,2}
        }));
    }
}
