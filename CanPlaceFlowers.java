public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1) return flowerbed[0] == 1 ? 0 >= n : 1 >= n;

        int planted = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                int j = i + 1;
                while (j < flowerbed.length && flowerbed[j] == 0) j++;

                if (i == 0 && j == flowerbed.length) {
                    planted += (j - i + 1) / 2;
                } else if (i == 0 || j == flowerbed.length) {
                    planted += (j - i) / 2;
                } else {
                    int empty = j - i;
                    planted += empty % 2 == 0 ? empty / 2 - 1 : empty / 2;
                }
                i = j;
            }
        }

        return planted >= n;
    }

    // https://leetcode.com/problems/can-place-flowers/solutions/3317843/java-c-simple-solution-easy-to-understand/
    class Solution {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            if (n == 0) {
                return true;
            }
            for (int i = 0; i < flowerbed.length; i++) {
                if (flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length-1 || flowerbed[i+1] == 0)) {
                    flowerbed[i] = 1;
                    n--;
                    if (n == 0) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
