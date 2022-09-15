import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 2007
public class FindOriginalArrayFromDoubledArray {
    public int[] findOriginalArray(int[] changed) {
        Arrays.sort(changed);
        if (changed.length % 2 != 0) return new int[0];
        int[] arr = new int[changed.length / 2];
        Map<Integer, Integer> map = new HashMap<>();
        for (var i : changed) {
            if (i % 2 == 0) {
                if (map.containsKey(i / 2)) {
                    map.put(i / 2, map.get(i / 2) + 1);
                    if (map.containsKey(i)) {
                        map.put(i, map.get(i) - 1);
                    }
                    continue;
                }
            }

            if (map.containsKey(i * 2)) {
                map.put(i * 2, map.get(i * 2) - 1);
                map.put(i, map.getOrDefault(i, 0) + 1);
            } else {
                map.put(i, map.getOrDefault(i, -1) + 1);
            }
        }

        int i = 0;
        for (var e : map.entrySet()) {
            if (e.getValue() >= 1) {
                arr[i] = e.getKey();
                i++;
            }
        }

        if (i != changed.length / 2) return new int[0];

        return arr;
    }

    // https://leetcode.com/problems/find-original-array-from-doubled-array/discuss/2577803/JAVA-oror-Easy-Solution-oror-100-Faster-Code-oror-Beginner-Friendly
    class Solution {
        int[] res;
        int index=0;
        public int[] findOriginalArray(int[] changed) {
            if(changed.length%2==1) return new int[0];
            res = new int[changed.length/2];

            int max = 0;
            for(int num: changed) max = Math.max(max, num);
            int[] nums = new int[max+1];
            for(int num: changed) nums[num]++;

            if(nums[0]%2==1) return new int[0];
            fill(0, nums[0]/2);

            for(int i=1; i<=max/2; i++){
                if(nums[2*i] < nums[i]) return new int[0];
                nums[2*i] -= nums[i];
                fill(i, nums[i]);
            }
            return index != res.length ? new int[0] : res;
        }

        private void fill(int num, int count){
            while(count-->0)
                res[index++] = num;
        }
    }

    public static void main(String[] args) {
        FindOriginalArrayFromDoubledArray f = new FindOriginalArrayFromDoubledArray();
        System.out.println(Arrays.toString(f.findOriginalArray(new int[]{
                1,2,7,2,4,14
        })));
    }
}
