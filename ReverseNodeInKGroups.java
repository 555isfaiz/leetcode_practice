import java.util.LinkedList;
import java.util.Queue;

// 25
public class ReverseNodeInKGroups {
    public ListNode reverseKGroupBetter(ListNode head, int k) {
        ListNode prev = null;
        ListNode current = head;
        int count = 0;
        ListNode next = null;

        //reverse blindly
        while (current != null && count < k) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        //restoring if count lesser
        if (count < k) {
            current = prev;
            prev = null;
            next = null;

            while (count != 0) {
                count--;
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
        }
        //head always contains first node (last node after reverse) current head will become current tail and next set should be attached to that
        if (next != null) {
            head.next = reverseKGroupBetter(next, k);
        }
        //prev contains current head and it should be returned
        return prev;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return head;

        ListNode h = head, hn = head.next;
        ListNode rStart, rEnd = null, rPrev = null, res = null;
        Queue<ListNode> queue = new LinkedList<>();
        int counter = 0;
        while (h != null) {
            queue.add(h);
            counter++;
            h = h.next;
            if (counter == k) {
                rEnd = rStart = queue.poll();
                while (!queue.isEmpty()) {
                    ListNode t = queue.poll();
                    t.next = rEnd;
                    rEnd = t;
                }

                if (rPrev != null) {
                    rPrev.next = rEnd;
                } else {
                    res = rEnd;
                }

                rPrev = rStart;
                rPrev.next = h;
                counter = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ReverseNodeInKGroups r = new ReverseNodeInKGroups();
        ListNode n = new ListNode(5);
        ListNode nn = new ListNode(4, n);
        ListNode nnn = new ListNode(3, nn);
        ListNode nnnn = new ListNode(2, nnn);
        ListNode nnnnn = new ListNode(1, nnnn);
        r.reverseKGroup(nnnnn, 2);
    }
}
