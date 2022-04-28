import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 1631
public class PathWithMinimumEffort {
    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Node)) {
                return false;
            }

            Node n = (Node)o;
            return this.x == n.x && this.y == n.y;
        }
    }
    public int search(int[][] heights, List<Integer> route, int currentEffort, int curX, int curY, boolean isBranch, int knownMax, int exclude, List<Node> nodes) {
        int lastMove = 0;
        int x = curX, y = curY;
        nodes.add(new Node(x, y));
        if (route.size() != 0) {
            lastMove = route.get(route.size() - 2);
        }

        int thisEffort = -1;
        int thisMove = 0;
        if (exclude != 1 && curY != heights[0].length - 1 && lastMove != 2 && curX - 1 >= 0 && !nodes.contains(new Node(x - 1, y))) {
            // up
            int effort = Math.abs(heights[curX][curY] - heights[curX - 1][curY]);
            if (thisEffort == -1 || thisEffort > effort) {
                thisMove = 1;
                thisEffort = effort;
            }
        }

        if (exclude != 3 && curX != heights.length - 1 && lastMove != 4 && curY - 1 >= 0 && !nodes.contains(new Node(x, y - 1))) {
            // left
            int effort = Math.abs(heights[curX][curY] - heights[curX][curY - 1]);
            if (thisEffort == -1 || thisEffort > effort) {
                thisMove = 3;
                thisEffort = effort;
            }
        }

        if (exclude != 2 && lastMove != 1 && curX + 1 < heights.length && !nodes.contains(new Node(x + 1, y))) {
            // down
            int effort = Math.abs(heights[curX][curY] - heights[curX + 1][curY]);
            if (thisEffort == -1 || thisEffort > effort) {
                thisMove = 2;
                thisEffort = effort;
            }
        }

        if (exclude != 4 && lastMove != 3 && curY + 1 < heights[0].length && !nodes.contains(new Node(x, y + 1))) {
            // right
            int effort = Math.abs(heights[curX][curY] - heights[curX][curY + 1]);
            if (thisEffort == -1 || thisEffort > effort) {
                thisMove = 4;
                thisEffort = effort;
            }
        }

        if (isBranch) {
            if (thisEffort > knownMax || thisMove == 0)
                return -1;
        }

        if (thisMove == 0) {
            nodes.remove(nodes.size() - 1);
            int lm = route.remove(route.size() - 2);
            int lastEffort = route.remove(route.size() - 1);
            if (lm == 1) {
                x++;
            } else if (lm == 2) {
                x--;
            } else if (lm == 3) {
                y++;
            } else if (lm == 4) {
                y--;
            }
            return search(heights, route, lastEffort, x, y, isBranch, knownMax, lm, nodes);
        }

        route.add(thisMove);
        route.add(currentEffort);
        if (thisMove == 1) {
            x--;
        } else if (thisMove == 2) {
            x++;
        } else if (thisMove == 3) {
            y--;
        } else if (thisMove == 4) {
            y++;
        }

        if (x == heights.length - 1 && y == heights[0].length - 1)
            return Math.max(thisEffort, currentEffort);
        else
            return search(heights, route, Math.max(thisEffort, currentEffort), x, y, isBranch, knownMax, 0, nodes);
    }
    // this solution failed
    public int minimumEffortPath(int[][] heights) {
        if (heights.length <= 1 && heights[0].length <= 1)
            return 0;
        List<Integer> path = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();
        int effort = search(heights, path, 0, 0, 0, false, 0, 0, nodes);
        int curX = heights.length - 1, curY = heights[0].length - 1;
        int count = path.size() / 2;
        for (int i = 0; i < count; i++) {
            int lastMove = path.remove(path.size() - 2);
            int lastEffort = path.remove(path.size() - 1);
            nodes.remove(nodes.size() - 1);
            if (lastMove == 1) {
                curX++;
            } else if (lastMove == 2) {
                curX--;
            } else if (lastMove == 3) {
                curY++;
            } else if (lastMove == 4) {
                curY--;
            }
            int e = search(heights, new ArrayList<>(path), lastEffort, curX, curY, true, effort, lastMove, new ArrayList<>(nodes));
            if (e != -1 && e < effort)
                effort = e;
        }
        return effort;
    }

    public int minimumEffortPathBetter(int[][] heights) {

        int n=heights.length;
        int m=heights[0].length;

        if(n==1 && m==1)
            return 0;

        int efforts[][]=new int[n][m];//stores the minimum effots required to travel upto a given cell
        for(int row[]:efforts)
            Arrays.fill(row,Integer.MAX_VALUE);

        PriorityQueue<int[]> pq=new PriorityQueue<int[]>((t1, t2)->(t1[2]-t2[2]));//returns cell with minimum efforts

        pq.offer(new int[]{0,0,0});
        int dir[][]={{0,1},{0,-1},{1,0},{-1,0}};//direction to travel

        while(!pq.isEmpty())
        {
            int curr[]=pq.poll();
            int curr_row=curr[0];
            int curr_col=curr[1];
            int curr_wt=curr[2];
            for(int x[]:dir)
            {
                int nrow=curr_row+x[0];
                int ncol=curr_col+x[1];
                if(nrow<0 || nrow>=n || ncol<0 || ncol>=m)
                    continue;
                int wt = Math.max(curr_wt,Math.abs(heights[nrow][ncol]-heights[curr_row][curr_col]));//getting max absolute value
                //updating the minimum effort
                if(wt<efforts[nrow][ncol])
                {
                    efforts[nrow][ncol]=wt;
                    pq.offer(new int[]{nrow,ncol,wt});
                }

            }
        }
        return efforts[n-1][m-1];
    }

    public static void main(String[] args) {
        int[][] h = new int[][]{{1,10,6,7,9,10,4,9}};
        PathWithMinimumEffort p = new PathWithMinimumEffort();
        System.out.println(p.minimumEffortPath(h));
    }
}
