import java.util.*;

// 1048
public class LongestStringChain {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int[] mark = new int[words.length];
        Arrays.fill(mark, 1);
        int max = 1;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].length() < words[j].length() - 1) break;
                if (words[i].length() != words[j].length() - 1) continue;
                boolean bear = true, miss = false;
                for (int ii = 0, jj = 0; ii < words[i].length() && jj < words[j].length(); ii++, jj++) {
                    if (words[i].charAt(ii) != words[j].charAt(jj)) {
                        if (bear) { bear = false; ii--; }
                        else { miss = true; break; }
                    }
                }
                if (!miss) { mark[j] = Math.max(mark[j], mark[i] + 1); max = Math.max(max, mark[j]); }
            }
        }
        return max;
    }

    // https://leetcode.com/problems/longest-string-chain/discuss/1213855/JS-Python-Java-C%2B%2B-or-Fast-Set-DP-Map-Solution-w-Explanation
    public int longestStrChainBetter(String[] words) {
        List<Set<String>> W = new ArrayList<>(17);
        for (int i = 0; i < 17; i++)
            W.add(new HashSet<>());
        for (String word : words)
            W.get(word.length()).add(word);
        Map<String, Integer> dp = new HashMap<>();
        int best = 1;
        for (int i = 16; i > 0; i--) {
            if (W.get(i-1).isEmpty()) continue;
            for (String word : W.get(i)) {
                int wVal = dp.getOrDefault(word, 1);
                for (int j = 0; j < word.length(); j++) {
                    String pred = word.substring(0,j) + word.substring(j+1);
                    if (W.get(i-1).contains(pred) && wVal >= dp.getOrDefault(pred,1)) {
                        dp.put(pred, wVal + 1);
                        best = Math.max(best, wVal + 1);
                    }
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        LongestStringChain l = new LongestStringChain();
        System.out.println(l.longestStrChain(new String[]{
                "czgxmxrpx","lgh","bj","cheheex","jnzlxgh","nzlgh","ltxdoxc","bju","srxoatl","bbadhiju","cmpx","xi","ntxbzdr","cheheevx","bdju","sra","getqgxi","geqxi","hheex","ltxdc","nzlxgh","pjnzlxgh","e","bbadhju","cmxrpx","gh","pjnzlxghe","oqlt","x","sarxoatl","ee","bbadju","lxdc","geqgxi","oqltu","heex","oql","eex","bbdju","ntxubzdr","sroa","cxmxrpx","cmrpx","ltxdoc","g","cgxmxrpx","nlgh","sroat","sroatl","fcheheevx","gxi","gqxi","heheex"
//                "a","b","ba","bca","bda","bdca"
//                "a","b","ab","bac"
        }));
    }
}
