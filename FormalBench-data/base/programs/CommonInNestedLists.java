
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

class CommonInNestedLists {
    
    public static int[] commonInNestedLists(int[][] nestedArray) {
        if (nestedArray.length == 0) {
            return new int[0];
        }

        Set<Integer> commonSet = new HashSet<>();
        for (int num : nestedArray[0]) {
            commonSet.add(num);
        }

        for (int i = 1; i < nestedArray.length; i++) {
            Set<Integer> tempSet = new HashSet<>();
            for (int num : nestedArray[i]) {
                tempSet.add(num);
            }
            commonSet.retainAll(tempSet);
        }

        int[] result = new int[commonSet.size()];
        int index = 0;
        for (Integer num : commonSet) {
            result[index++] = num;
        }

        return result;
    }
}

