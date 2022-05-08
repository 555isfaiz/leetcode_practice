import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 341
public class FlattenNestedListIterator {
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {
        List<NestedInteger> list;
        List<Integer> indices = new ArrayList<>();
        List<Integer> nextIndices = new ArrayList<>();
        List<NestedInteger> cache;

        public NestedIterator(List<NestedInteger> nestedList) {
            list = nestedList;
        }

        NestedInteger walk() {
            if (cache != null && !indices.isEmpty())
                return cache.get(indices.get(indices.size() - 1));

            NestedInteger ptr = null;
            for (var i : indices) {
                if (ptr == null) {
                    ptr = list.get(i);
                } else {
                    ptr = ptr.getList().get(i);
                }
            }
            return ptr;
        }

        List<NestedInteger> walkToList(List<Integer> ind) {
            List<NestedInteger> ptr = list;
            for (int i = 0; i < ind.size() - 1; i++) {
                int ii = ind.get(i);
                ptr = ptr.get(ii).getList();
            }

            return ptr;
        }

        @Override
        public Integer next() {
            updateNext();

            indices.clear();
            indices.addAll(nextIndices);
            nextIndices.clear();

            return walk().getInteger();
        }

        boolean findNext(List<NestedInteger> startList) {
            if (nextIndices.isEmpty())
                return false;

            int index = nextIndices.remove(nextIndices.size() - 1) + 1;
            if (startList.isEmpty() || index >= startList.size()) {
                return findNext(walkToList(nextIndices));
            }

            var i = startList.get(index);
            nextIndices.add(index);
            if (i.isInteger()) {
                cache = startList;
                return true;
            }
            else {
                nextIndices.add(-1);
                return findNext(i.getList());
            }
        }

        boolean updateNext() {
            if (!nextIndices.isEmpty())
                return true;

            nextIndices.addAll(indices);
            var l = walkToList(nextIndices);
            int i = 0;
            if (!nextIndices.isEmpty())
                i = nextIndices.remove(nextIndices.size() - 1) + 1;

            List<NestedInteger> ptr;
            if (i < l.size()) {
                nextIndices.add(i);
                if (l.get(i).isInteger()) {
                    cache = l;
                    return true;
                } else {
                    nextIndices.add(-1);
                    ptr = l.get(i).getList();
                }
            } else {
                ptr = walkToList(nextIndices);
            }

            return findNext(ptr);
        }

        @Override
        public boolean hasNext() {
            return updateNext();
        }
    }

    // https://leetcode.com/problems/flatten-nested-list-iterator/discuss/2018329/Java-Clean
    public class NestedIteratorBetter implements Iterator<Integer> {

        private List<Integer> integerList = new ArrayList<>();
        private int index = 0;
        public NestedIteratorBetter(List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger : nestedList) {
                flatten(nestedInteger);
            }
        }

        private void flatten(NestedInteger nested) {
            if (nested.isInteger())
                integerList.add(nested.getInteger());
            else
                for (NestedInteger nestedFromList : nested.getList()) {
                    flatten(nestedFromList);
                }
        }

        @Override
        public boolean hasNext() {
            return index < integerList.size();
        }

        @Override
        public Integer next() {
            return integerList.get(index++);
        }
    }

    public static void main(String[] args) {

    }
}
