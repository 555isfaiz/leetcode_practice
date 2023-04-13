import java.util.HashMap;
import java.util.Map;

public class ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < popped.length; i++) {
            m.put(popped[i], i);
        }

        for (int i = 0; i < pushed.length; i++) {
            int index_1 = m.get(pushed[i]), index_2;
            for (int j = i + 1; j < pushed.length; j++) {
                index_2 = m.get(pushed[j]);
                if (index_2 < index_1) return false;
            }
        }
        return true;
    }

    // https://leetcode.com/problems/validate-stack-sequences/solutions/1853250/java-c-space-complexity-going-from-o-n-o-1/
    class Solution {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            int i = 0; // Intialise one pointer pointing on pushed array
            int j = 0; // Intialise one pointer pointing on popped array
            
            for(int val : pushed){
                pushed[i++] = val; // using pushed as the stack.
                while(i > 0 && pushed[i - 1] == popped[j]){ // pushed[i - 1] values equal to popped[j];
                    i--; // decrement i
                    j++; // increment j
                }
            }
            return i == 0; // Since pushed is a permutation of popped so at the end we are supposed to be left with an empty stack
        }
    }

    public static void main(String[] args) {
        ValidateStackSequences v = new ValidateStackSequences();
        System.out.println(v.validateStackSequences(new int[] {4,0,1,2,3},new int[] {4,2,3,0,1}));
    }
}
