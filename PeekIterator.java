// 284
import java.util.Iterator;

public class PeekIterator {

    class PeekingIterator implements Iterator<Integer> {
        int cursor = 0;
        int[] array = new int[1024];
        int length = 0;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            int index = 0;
            while (iterator.hasNext()) {
                array[index++] = iterator.next();
            }
            length = index;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return array[cursor];
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            return array[cursor++];
        }

        @Override
        public boolean hasNext() {
            return cursor < length;
        }
    }

    class PeekingIteratorBetter implements Iterator<Integer> {
        Iterator<Integer> iter = null;
        Integer next = null;

        public PeekingIteratorBetter(Iterator<Integer> iterator) {
            // initialize any member here.
            iter = iterator;
            if(hasNext()) next = iter.next();
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return next;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            Integer currVal = next;
            if(iter.hasNext())
                next = iter.next();
            else
                next = null;
            return currVal;
        }

        @Override
        public boolean hasNext() {
            return next != null || iter.hasNext();
        }
    }

    public static void main(String[] args) {
    }
}