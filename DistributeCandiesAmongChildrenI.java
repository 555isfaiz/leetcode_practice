public class DistributeCandiesAmongChildrenI {
    public int distributeCandies(int n, int limit) {
        int count = 0;
        for (int i = 0; i <= limit; i++) {
            for (int j = 0; j <= Math.min(limit, n - i); j++) {
                if (n - i - j <= limit) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        var d = new DistributeCandiesAmongChildrenI();
        System.out.println(d.distributeCandies(5, 2));
    }
}
