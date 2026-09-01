
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

		for (int id = 1; id < nestedArray.length; id++) {
			Set<Integer> common = new HashSet<>();
			for (int num : nestedArray[id]) {
				common.add(num);
			}
			elements.retainAll(common);
		}

		int[] nested = new int[elements.size()];
		int index = 0;
		for (Integer number : elements) {
			nested[index++] = number;
		}

		return nested;
	}
}
