import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class SignoftheProductofanArray {
    public int arraySign(int[] nums) {
        Supplier<IntStream> sup = () -> Arrays.stream(nums);
        if (sup.get().anyMatch(i -> i == 0))
            return 0;
        if (sup.get().filter(i -> i < 0).count() % 2 == 0)
            return 1;
        else {
            return -1;
        }
    }

    public int arraySignFor(int[] nums) {
        int minus = 0;
        for (var i : nums) {
            if (i == 0)
                return 0;
            if (i < 0)
                minus++;
        }
        return minus % 2 == 0 ? 1 : -1;
    }

    public static void main(String[] args) {
        SignoftheProductofanArray s = new SignoftheProductofanArray();
        System.out.println(s.arraySign(new int[] {41,65,14,80,20,10,55,58,24,56,28,86,96,10,3,84,4,41,13,32,42,43,83,78,82,70,15,-41}));
    }
}
