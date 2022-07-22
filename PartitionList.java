import java.util.LinkedList;
import java.util.Queue;

// 86
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        Queue<ListNode> before = new LinkedList<>();
        Queue<ListNode> after = new LinkedList<>();
        ListNode h = null, ptr = head;
        while (ptr != null) {
            if (ptr.val < x) {
                before.add(ptr);
                while (ptr.next != null && ptr.next.val < x) ptr = ptr.next;
            } else {
                after.add(ptr);
                while (ptr.next != null && ptr.next.val >= x) ptr = ptr.next;
            }

            var t = ptr;
            ptr = ptr.next;
            t.next = null;
        }

        int size = before.size();
        for (int i = 0; i < size; i++) {
            var n = before.poll();
            if (i == 0) h = n;
            else ptr.next = n;
            ptr = n;
            while (ptr.next != null) ptr = ptr.next;
        }

        size = after.size();
        for (int i = 0; i < size; i++) {
            var n = after.poll();
            if (i == 0 && h == null) h = n;
            else ptr.next = n;
            ptr = n;
            while (ptr.next != null) ptr = ptr.next;
        }
        return h;
    }

    // https://leetcode.com/problems/partition-list/discuss/29183/Concise-java-code-with-explanation-one-pass
    public ListNode partitionBetter(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
        ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
        while (head!=null){
            if (head.val<x) {
                curr1.next = head;
                curr1 = head;
            }else {
                curr2.next = head;
                curr2 = head;
            }
            head = head.next;
        }
        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
        curr1.next = dummy2.next;
        return dummy1.next;
    }
}
