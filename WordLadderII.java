import java.util.*;

// 126
public class WordLadderII {
    int len = -1;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        List<String> wl = new ArrayList<>(wordList);
        if (!wl.contains(beginWord)) { wl.add(beginWord); }
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < wl.size(); i++) {
            for (int j = i; j < wl.size(); j++) {
                var s = wl.get(i);
                var ss = wl.get(j);
                if (s.equals(ss)) continue;
                boolean diff = false, valid = true;
                for (int ii = 0; ii < s.length(); ii++) {
                    if (s.charAt(ii) != ss.charAt(ii)) {
                        if (diff) {
                            valid = false;
                            break;
                        }
                        else diff = true;
                    }
                }
                if (valid) {
                    if (map.containsKey(s)) {
                        map.get(s).add(ss);
                    } else {
                        List<String> l = new ArrayList<>();
                        l.add(ss);
                        map.put(s, l);
                    }
                    if (map.containsKey(ss)) {
                        map.get(ss).add(s);
                    } else {
                        List<String> l = new ArrayList<>();
                        l.add(s);
                        map.put(ss, l);
                    }
                }
            }
        }

        var init = map.get(beginWord);
        if (init != null) {
            for (var s : init) {
                List<String> trans = new ArrayList<>();
                trans.add(beginWord); trans.add(s);
                helper(endWord, result, map, trans, new HashMap<>());
            }

            result.removeIf(l -> l.size() > len);
        }

        return result;
    }

    boolean helper(String beginWord, List<List<String>> result, Map<String, List<String>> map, List<String> trans, Map<String, List<List<String>>> dp) {
        if (len != -1 && trans.size() > len)
            return false;
        if (trans.get(trans.size() - 1).equals(beginWord)) {
            len = trans.size();
            result.add(new ArrayList<>(trans));
            return true;
        }

        var last = trans.get(trans.size() - 1);
        if (dp.containsKey(last)) {
            var ls = dp.get(last);
            for (var ll : ls) {
                int size = trans.size();
                List<String> tmp = new ArrayList<>(trans);
                tmp.addAll(ll);
                if (helper(beginWord, result, map, tmp, dp)) {
                    var tt = new ArrayList<>(ll);
                    for (int i = size - 2; i > 0; i--) {
                        tt.add(0, trans.get(i));
                        if (dp.containsKey(trans.get(i))) {
                            dp.get(trans.get(i)).add(new ArrayList<>(tt));
                        } else {
                            List<List<String>> lll = new ArrayList<>();
                            lll.add(new ArrayList<>(tt));
                            dp.put(trans.get(i), lll);
                        }
                    }
                }
            }
        } else {
            var l = map.get(last);
            for (var s : l) {
                if (trans.contains(s)) continue;
                trans.add(s);
                if (helper(beginWord, result, map, trans, dp)) {
                    int size = trans.size();
                    List<String> tt = new ArrayList<>();
                    for (int i = size - 1; i > 0; i--) {
                        tt.add(0, trans.get(i));
                        if (dp.containsKey(trans.get(i - 1))) {
                            dp.get(trans.get(i - 1)).add(new ArrayList<>(tt));
                        } else {
                            List<List<String>> lll = new ArrayList<>();
                            lll.add(new ArrayList<>(tt));
                            dp.put(trans.get(i - 1), lll);
                        }
                    }
                }
                trans.remove(trans.size() - 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordLadderII w = new WordLadderII();
        System.out.println(w.findLadders("hit", "cog", List.of(
                "hot","dot","dog","lot","log","cog"
        )));
    }
}
