// 1342
public class NumberofStepstoReduceaNumbertoZero {
    public int numberOfSteps(int num) {
        int step = 0;
        while (num > 0) {
            if (num % 2 == 0) num /= 2;
            else num--;
            step++;
        }
        return step;
    }

    public static void main(String[] args) {
        NumberofStepstoReduceaNumbertoZero n = new NumberofStepstoReduceaNumbertoZero();
        System.out.println(n.numberOfSteps(14));
    }
}
