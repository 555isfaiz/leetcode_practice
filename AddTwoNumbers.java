// 2
public class AddTwoNumbers {
  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode l1_cursor = l1;
    ListNode l2_cursor = l2;
    boolean forward = false;
    while (true) {
      int sum = l1_cursor.val + l2_cursor.val + (forward ? 1 : 0);
      l1_cursor.val = sum % 10;
      forward = sum / 10 > 0;
      boolean end = false;
      if (l1_cursor.next == null) {
        l1_cursor.next = l2_cursor.next;
        end = true;
      } else if (l2_cursor.next == null) {
        end = true;
      }

      if (end) {
        while (forward) {
          if (l1_cursor.next != null) {
            int s = l1_cursor.next.val + 1;
            l1_cursor.next.val = s % 10;
            l1_cursor = l1_cursor.next;
            forward = s / 10 > 0;
          } else {
            l1_cursor.next = new ListNode(1);
            forward = false;
          }
        }
        break;
      } else {
        l1_cursor = l1_cursor.next;
        l2_cursor = l2_cursor.next;
      }
    }

    return l1;
  }

  public static void main(String[] args) {
    ListNode n = new ListNode(9);
    ListNode nn = new ListNode(9, n);
    ListNode nnn = new ListNode(9, nn);
    ListNode nnnn = new ListNode(9, nnn);
    ListNode nnnnn = new ListNode(9);
    ListNode nnnnnn = new ListNode(2);
    ListNode nnnnnnn = new ListNode(9, nnnnnn);
    ListNode n1 = new ListNode(9);
    ListNode n2 = new ListNode(4, n1);
    ListNode n3 = new ListNode(7);
    ListNode n4 = new ListNode(3, n3);
    addTwoNumbers(nnnnnnn, n4);
  }
}
