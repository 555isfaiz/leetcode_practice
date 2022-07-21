import java.util.Stack;

// 92
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode before = null, after = null, ptr = head, h = head;
        Stack<ListNode> stack = new Stack<>();
        for (int i = 0; i < right; i++) {
            if (i == left - 2) before = ptr;
            if (i == right - 1) after = ptr.next;
            if (i >= left - 1) stack.push(ptr);
            ptr = ptr.next;
        }

        int size = stack.size();
        for (int i = 0; i < size; i++) {
            var t = stack.pop();
            if (i == 0 && before != null) before.next = t;
            else if (i == 0) h = t;
            if (i == size - 1) t.next = after;
            if (i > 0) ptr.next = t;
            ptr = t;
        }

        return h;
    }

    public static void main(String[] args) {
        ReverseLinkedListII r = new ReverseLinkedListII();
        ListNode ll = new ListNode(5);
        ListNode l = new ListNode(3, ll);
        r.reverseBetween(l, 1, 2);
    }
}
