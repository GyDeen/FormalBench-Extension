
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

class CommonInNestedLists {

	public static int[] commonInNestedLists(int[][] nestedArray) {
		if (nestedArray.length == 0) {
			return new int[0];
		}

		Set<Integer> elements = new HashSet<>();
		for (int num : nestedArray[0]) {
			elements.add(num);
		}

		for (int len = 1; len < nestedArray.length; len++) {
			Set<Integer> common = new HashSet<>();
			for (int num : nestedArray[len]) {
				common.add(num);
			}
			elements.retainAll(common);
		}

		int[] nested = new int[elements.size()];
		int index = 0;
		for (Integer id : elements) {
			nested[index++] = id;
		}

		return nested;
	}
}
