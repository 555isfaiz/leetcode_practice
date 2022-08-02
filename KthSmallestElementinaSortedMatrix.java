import java.util.PriorityQueue;

// 378
public class KthSmallestElementinaSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int[] mover = new int[matrix.length];
        int index = 0;
        for (int i = 0; i < k - 1; i++) {
            if (mover[index] == matrix.length - 1) {
                mover[index] = -1;
                int min = Integer.MAX_VALUE, toswap = 0;
                for (int j = 0; j < matrix.length; j++) {
                    if (j == index) continue;
                    if (mover[j] != -1
                            && matrix[j][mover[j]] <= min) {
                        toswap = j;
                        min = matrix[j][mover[j]];
                    }
                }
                index = toswap;
                continue;
            }
            int min = matrix[index][mover[index] + 1];
            int toswap = -1;
            for (int j = 0; j < matrix.length; j++) {
                if (j == index) continue;
                if (mover[j] != -1
                        && matrix[j][mover[j]] <= min) {
                    toswap = j;
                    min = matrix[j][mover[j]];
                }
            }
            mover[index]++;
            if (toswap != -1) index = toswap;
        }
        return matrix[index][mover[index]];
    }

    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85173/Share-my-thoughts-and-Clean-Java-Code
    public class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
            for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
            for(int i = 0; i < k-1; i++) {
                Tuple t = pq.poll();
                if(t.x == n-1) continue;
                pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
            }
            return pq.poll().val;
        }
    }

    class Tuple implements Comparable<Tuple> {
        int x, y, val;
        public Tuple (int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo (Tuple that) {
            return this.val - that.val;
        }
    }

    public static void main(String[] args) {
        KthSmallestElementinaSortedMatrix k = new KthSmallestElementinaSortedMatrix();
        System.out.println(k.kthSmallest(new int[][] {
                {1,1,3,8,13},{4,4,4,8,18},{9,14,18,19,20},{14,19,23,25,25},{18,21,26,28,29}
        }, 13));
    }
}
