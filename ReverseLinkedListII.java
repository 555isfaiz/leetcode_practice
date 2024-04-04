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

    public ListNode reverseBetweenNew(ListNode head, int left, int right) {
        ListNode cur = head, ptr = head, h = head, s = head;
        int i = 0;
        while (ptr != null) {
            i++;
            if (i < left) {
                ptr = ptr.next;
                cur = cur.next;
                h = h.next;
                if (i != 1)
                    s = s.next;
                continue;
            }
            else if (i > right)
                break;
            var tmp = ptr.next;
            ptr.next = cur;
            cur = ptr;
            ptr = tmp;
        }
        h.next = ptr;
        if (s != h) {
            s.next = cur;
            return head;
        }
        else
            return cur;
    }

    public static void main(String[] args) {
        ReverseLinkedListII r = new ReverseLinkedListII();
        ListNode ll = new ListNode(5);
        ListNode l = new ListNode(3, ll);
        r.reverseBetween(l, 1, 2);
    }
}
