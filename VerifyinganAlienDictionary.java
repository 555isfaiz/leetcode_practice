import java.util.*;

public class VerifyinganAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[order.length()];
        for (int i = 0; i < index.length; i++) {
            index[order.charAt(i) - 'a'] = i;
        }

        int[] bufs = new int[20];
        Arrays.fill(bufs, -1);
        
        for (int i = 0; i < words.length; i++) {
            int j = 0;
            boolean check = true;
            for (; j < words[i].length(); j++) {
                if (bufs[j] != -1 && index[words[i].charAt(j) - 'a'] < bufs[j])
                    return false;
                else if (bufs[j] == -1) {
                    if (i != 0 && words[i - 1].length() > j && index[words[i].charAt(j) - 'a'] < index[words[i - 1].charAt(j) - 'a'])
                        return false;
                    else if (i != 0 && words[i - 1].length() > j && index[words[i].charAt(j) - 'a'] > index[words[i - 1].charAt(j) - 'a']) {
                        check = false;
                    }

                    bufs[j] = index[words[i].charAt(j) - 'a'];

                    if (i == 0)
                        break;
                } else if (index[words[i].charAt(j) - 'a'] > bufs[j]) {
                    bufs[j] = index[words[i].charAt(j) - 'a'];
                    check = false;
                    break;
                }
            }

            if (check && j == words[i].length() && i != 0 && j < words[i - 1].length())
                return false;
        }
        return true;
    }

    // https://leetcode.com/problems/verifying-an-alien-dictionary/solutions/3129280/easy-beats-100-with-video-java-c-python/
    int [] orderMap = new int[26];
    public boolean isAlienSortedBetter(String[] words, String order) {
        
        for (int i = 0; i < order.length(); i++){
            orderMap[order.charAt(i) - 'a'] = i;
        }
        
        for(int i =1;i< words.length;i++){
            if(!compare(words[i],words[i-1]))return false;
        }
        return true;
        
    }
    
    public boolean compare(String s1, String s2){
        
            int j = 0;
            while(j<s1.length() && j<s2.length()){
                if(s1.charAt(j) == s2.charAt(j)) j++;
                else if(orderMap[s1.charAt(j)-'a']>orderMap[s2.charAt(j)-'a']) return true;
                else return false;
            }
            if(s1.length()<s2.length())return false;
            return true;
        
    }

    public static String getLargestNumber(String num) {
        Character[] arr = new Character[num.length()];
        arr[0] = num.charAt(0);
        boolean even = (arr[0] - '0') % 2 == 0;
        int start = 0;
        List<int[]> ranges = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            arr[i] = num.charAt(i);
            boolean tmp = (arr[i] - '0') % 2 == 0;
            if (tmp != even) {
                ranges.add(new int[] {start, i});
                start = i;
                even = tmp;
            }
        }

        if (start != arr.length - 1)
            ranges.add(new int[] {start, arr.length});

        for (var r : ranges)
            Arrays.sort(arr, r[0], r[1], (a,b) -> b - a);
        
        StringBuilder sb = new StringBuilder();
        for (var c : arr)
            sb.append(c);
        return sb.toString();
    }

    static Map<String, Integer> m = new HashMap<>();
    static String getKey(String str) {
        char[] c = str.toCharArray();
        Arrays.sort(c);
        String ss = String.copyValueOf(c);
        return ss;
    }

    public static List<Long> substitutions(List<String> words, List<String> phrases) {
        List<Long> res = new ArrayList<>();
        for (var s : words) {
            m.compute(getKey(s), (k,v) -> {return v == null ? 1 : v + 1;});
        }

        for (var phrase : phrases) {
            long combo = 0;
            var ws = phrase.split(" ");
            for (var w : ws) {
                var key = getKey(w);
                if (m.containsKey(key)) {
                    var val = m.get(key);
                    combo = combo == 0 ? val : combo * val;
                }
            }
            res.add(combo);
        }

        return res;
    }

    public static void main(String[] args) {
        VerifyinganAlienDictionary v = new VerifyinganAlienDictionary();
        System.out.print(v.isAlienSorted(new String[] {"apap", "app"}, "abcdefghijklmnopqrstuvwxyz"));
    }
}
