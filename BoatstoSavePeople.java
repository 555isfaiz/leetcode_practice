import java.util.Arrays;

public class BoatstoSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int count = people.length;
        for (int i = 0; i < people.length; i++) {
            if (people[i] < 0)
                continue;
            for (int j = people.length - 1; j > i; j--) {
                if (people[j] > 0 && people[j] + people[i] <= limit) {
                    people[j] = -1;
                    count--;
                    break;
                }
            }
        }
        return count;
    }

    // https://leetcode.com/problems/boats-to-save-people/solutions/1877945/java-c-a-very-easy-explanation-trust-me/
    class Solution {
        public int numRescueBoats(int[] people, int limit) {
            int boatCount = 0;
            Arrays.sort(people);
            
            int left = 0;
            int right = people.length - 1;
            
            while(left <= right){
                int sum = people[left] + people[right];
                if(sum <= limit){
                    boatCount++;
                    left++;
                    right--;
                }
                else{
                    boatCount++;
                    right--;
                }
            }
            return boatCount;
        }
    }

    public static void main(String[] args) {
        BoatstoSavePeople b = new BoatstoSavePeople();
        b.numRescueBoats(new int[] {3,2,2,1}, 3);
    }
}