import java.util.Arrays;

// 1710
public class MaximumUnitsonaTruck {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int val = 0;
        int ptr = 0;
        while (truckSize > 0 && ptr < boxTypes.length) {
            if (truckSize > boxTypes[ptr][0]) {
                val += boxTypes[ptr][1] * boxTypes[ptr][0];
                truckSize -= boxTypes[ptr][0];
            } else {
                val += boxTypes[ptr][1] * truckSize;
                truckSize = 0;
            }
            ptr++;
        }
        return val;
    }
}
