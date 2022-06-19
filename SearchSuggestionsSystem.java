import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

// 1268
public class SearchSuggestionsSystem {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products, String::compareTo);
        List<List<String>> result = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < searchWord.length(); i++) {
            sb.append(searchWord.charAt(i));
            List<String> t = new ArrayList<>(tmp);
            tmp.clear();
            if (t.isEmpty()) {
                for (var w : products) {
                    if (w.startsWith(sb.toString())) {
                        tmp.add(w);
                    }
                }
            } else {
                for (var w : t) {
                    if (w.startsWith(sb.toString())) {
                        tmp.add(w);
                    }
                }
            }

            List<String> toAdd = new ArrayList<>();
            for (int j = 0; j < 3 && j < tmp.size(); j++) {
                toAdd.add(tmp.get(j));
            }
            result.add(toAdd);
        }
        return result;
    }

    // https://leetcode.com/problems/search-suggestions-system/discuss/436674/C%2B%2BJavaPython-Sort-and-Binary-Search-the-Prefix
    public List<List<String>> suggestedProductsBetter(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        Arrays.sort(products);
        List<String> productsList = Arrays.asList(products);

        for (int i = 0; i < products.length; i++) {
            map.put(products[i], i);
        }

        String key = "";
        for (char c : searchWord.toCharArray()) {
            key += c;
            String ceiling = map.ceilingKey(key);
            String floor = map.floorKey(key + "~");
            if (ceiling == null || floor == null) break;
            res.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
        }

        while (res.size() < searchWord.length()) res.add(new ArrayList<>());
        return res;
    }
}
