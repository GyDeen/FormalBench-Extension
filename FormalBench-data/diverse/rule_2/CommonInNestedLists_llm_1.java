
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

		int array = 1;
		while (array < nestedArray.length) {
			Set<Integer> tempSet = new HashSet<>();
			for (int num : nestedArray[array]) {
				tempSet.add(num);
			}
			commonSet.retainAll(tempSet);
			array++;
		}

		int[] result = new int[commonSet.size()];
		int index = 0;
		for (Integer num : commonSet) {
			result[index++] = num;
		}

		return result;
	}
}
