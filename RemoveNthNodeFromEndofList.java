import java.util.ArrayList;
import java.util.List;

//19
public class RemoveNthNodeFromEndofList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> stack = new ArrayList<>();
        ListNode ptr = head;
        while (ptr != null) {
            stack.add(ptr);
            ptr = ptr.next;
        }

        ListNode nth = stack.get(stack.size() - n), nm1 = null;

        if (n != stack.size()) {
            nm1 = stack.get(stack.size() - n - 1);
            nm1.next = nth.next;
        } else {
            // nth is head
            head = nth.next;
        }

        return head;
    }

    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/submissions/
    public ListNode removeNthFromEndBetter(ListNode head, int n) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < n; i++)
            fast = fast.next;

        if (fast == null)
            return head.next;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
    }
}
