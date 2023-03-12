public class MergekSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        if (lists == null || lists.length == 0) {
            return res;
        }

        res = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode p1 = res, p2 = lists[i], head = null, cur = null, next = null;
            while (p1 != null || p2 != null) {
                if (p1 != null && p2 == null) {
                    next = p1;
                    p1 = p1.next;
                }
                else if (p1 == null && p2 != null) {
                    next = p2;
                    p2 = p2.next;
                }
                else {
                    if (p1.val < p2.val) {
                        next = p1;
                        p1 = p1.next;
                    }
                    else {
                        next = p2;
                        p2 = p2.next;
                    }
                }

                if (head == null) {
                    head = next;
                    cur = head;
                } else 
                    cur.next = next;
                cur = next;
            }
            res = head;
        }
        return res;
    }

    // https://leetcode.com/problems/merge-k-sorted-lists/solutions/3285977/hard-made-it-easy-easily-explained-by-divide-and-conquer-method/
    class Better {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            return helper(lists, 0, lists.length - 1);
        }

        public ListNode helper(ListNode[] lists, int start, int end) {
            if (start == end) {
                return lists[start];
            }
            int mid = (start+end) / 2;
            ListNode left = helper(lists, start, mid);
            ListNode right = helper(lists, mid + 1, end);
            return merge(left, right);
        }

        public ListNode merge(ListNode left, ListNode right) {
            ListNode head = new ListNode(-1);
            ListNode temp = head;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    temp.next = left;
                    left = left.next;
                } else {
                    temp.next = right;
                    right = right.next;
                }
                temp = temp.next;
            }
            while (left != null) {
                temp.next = left;
                left = left.next;
                temp = temp.next;
            }
            while (right != null) {
                temp.next = right;
                right = right.next;
                temp = temp.next;
            }
            return head.next;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(4, n1);
        ListNode n3 = new ListNode(1, n2);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(3, n4);
        ListNode n6 = new ListNode(1, n5);
        ListNode n7 = new ListNode(6);
        ListNode n8 = new ListNode(2, n7);
        MergekSortedLists m = new MergekSortedLists();
        // m.mergeKLists(new ListNode[] {n3, n6, n8});
        m.mergeKLists(new ListNode[] {null, n7});
    }
}
