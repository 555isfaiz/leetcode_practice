import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 745
public class PrefixandSuffixSearch {
    static class WordFilter {
        class WFListNode {
            Map<Character, WFListNode> chain = new HashMap<>();
            List<Integer> indices = new ArrayList<>();
        }

        Map<Character, WFListNode> prefix = new HashMap<>();
        Map<Character, WFListNode> suffix = new HashMap<>();
        public WordFilter(String[] words) {
            for (int ii = 0; ii < words.length; ii++) {
                var w = words[ii];
                WFListNode preLast = null;
                WFListNode sufLast = null;
                for (int i = 0, j = w.length() - 1; i < w.length() && j >= 0; i++, j--) {
                    if (i == 0 && j == w.length() - 1) {
                        if (prefix.containsKey(w.charAt(i))) { prefix.get(w.charAt(i)).indices.add(ii); preLast = prefix.get(w.charAt(i));}
                        else { var node = new WFListNode(); node.indices.add(ii); prefix.put(w.charAt(i), node); preLast = node; }

                        if (suffix.containsKey(w.charAt(j))) { suffix.get(w.charAt(j)).indices.add(ii); sufLast = suffix.get(w.charAt(j)); }
                        else { var node = new WFListNode(); node.indices.add(ii); suffix.put(w.charAt(j), node); sufLast = node; }
                    } else {
                        if (preLast.chain.containsKey(w.charAt(i))) { preLast.chain.get(w.charAt(i)).indices.add(ii); preLast = preLast.chain.get(w.charAt(i));}
                        else { var node = new WFListNode(); node.indices.add(ii); preLast.chain.put(w.charAt(i), node); preLast = node; }

                        if (sufLast.chain.containsKey(w.charAt(j))) { sufLast.chain.get(w.charAt(j)).indices.add(ii); sufLast = sufLast.chain.get(w.charAt(j)); }
                        else { var node = new WFListNode(); node.indices.add(ii); sufLast.chain.put(w.charAt(j), node); sufLast = node; }
                    }
                }
            }
        }

        public int f(String prefix, String suffix) {
            WFListNode prePtr = null;
            for (int i = 0; i < prefix.length(); i++) {
                if (prePtr == null) {
                    if (i == 0) {
                        prePtr = this.prefix.get(prefix.charAt(i));
                        if (prePtr == null) return -1;
                    } else {
                        return -1;
                    }
                    continue;
                }

                prePtr = prePtr.chain.get(prefix.charAt(i));
            }

            WFListNode sufPtr = null;
            for (int i = suffix.length() - 1; i >= 0; i--) {
                if (sufPtr == null) {
                    if (i == suffix.length() - 1) {
                        sufPtr = this.suffix.get(suffix.charAt(i));
                        if (sufPtr == null) return -1;
                    } else {
                        return -1;
                    }
                    continue;
                }

                sufPtr = sufPtr.chain.get(suffix.charAt(i));
            }

            if (prePtr == null || sufPtr == null) return -1;

            for (int i = prePtr.indices.size() - 1; i >= 0; i--) {
                if (sufPtr.indices.contains(prePtr.indices.get(i))) {
                    return prePtr.indices.get(i);
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        WordFilter wf = new WordFilter(new String[]{"cabaabaaaa","ccbcababac","bacaabccba","bcbbcbacaa","abcaccbcaa","accabaccaa","cabcbbbcca","ababccabcb","caccbbcbab","bccbacbcba"});
        System.out.println(wf.f("ab", "abcaccbcaa"));
    }
}
