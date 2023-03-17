import java.util.HashMap;

public class ImplementTrie {
    public class Trie {

        HashMap<Character, Trie> successors = new HashMap<>(); 
        Character c = '1';
        public boolean hasTerminate = false;

        public Trie() {}

        public Trie(char cc) {
            c = cc;
        }
        
        public void insert(String word, int index) {
            successors.putIfAbsent(word.charAt(index), new Trie(word.charAt(index)));

            if (index < word.length() - 1)
                successors.get(word.charAt(index)).insert(word, index + 1);
            else
                successors.get(word.charAt(index)).hasTerminate = true;
        }

        public void insert(String word) {
            if (word.isBlank())
                return;

            successors.putIfAbsent(word.charAt(0), new Trie(word.charAt(0)));
            var t = successors.get(word.charAt(0));

            if (word.length() > 1)
                t.insert(word, 1);
            else
                t.hasTerminate = true;
        }
        
        boolean walk(String w, int index, boolean notTerminate) {
            if (index < w.length()) {
                if (successors.containsKey(w.charAt(index)))
                    return successors.get(w.charAt(index)).walk(w, index + 1, notTerminate);
                else 
                    return false;
            } else 
                return notTerminate || hasTerminate;
        }

        public boolean search(String word) {
            if (word.isBlank())
                return false;
    
            if (successors.containsKey(word.charAt(0)))
                return successors.get(word.charAt(0)).walk(word, 1, false);
            else 
                return false;
        }

        public boolean startsWith(String prefix) {
            if (prefix.isBlank())
                return false;

            if (successors.containsKey(prefix.charAt(0)))
                return successors.get(prefix.charAt(0)).walk(prefix, 1, true);
            else 
                return false;
        }
    }

    class TrieNode {
        boolean isCompleteWord;
        TrieNode[] children;
        
        public TrieNode() {
            isCompleteWord = false; // when the word is complete (mark that node as true)
            children = new TrieNode[26]; // for 26 possible Childrens (for next letter)
        }
    }

    // https://leetcode.com/problems/implement-trie-prefix-tree/solutions/3305923/image-explanation-complete-stepwise-diagrams-c-java-python/
    // idea is the same, just better optimization
    class TrieBetter {
        TrieNode root;
        
        public TrieBetter() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isCompleteWord = true;
        }
        
        public boolean search(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    return false;
                }
                node = node.children[index];
            }
            return node.isCompleteWord;
        }
        
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    return false;
                }
                node = node.children[index];
            }
            return true;
        }
    }

    public static void main(String[] args) {
        ImplementTrie i = new ImplementTrie();
        Trie t = i.new Trie();
        t.insert("apple");
        t.search("apple");
        t.startsWith("a");
    }
}
