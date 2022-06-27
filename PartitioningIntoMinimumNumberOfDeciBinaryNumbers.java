// 1689
public class PartitioningIntoMinimumNumberOfDeciBinaryNumbers {
    public int minPartitions(String n) {
        int val = 0;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) - 48 > val) val = n.charAt(i) - 48;
        }
        return val;
    }
}
