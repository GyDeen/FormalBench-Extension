
import java.util.Arrays;

class JoinTuples {

	public static int[][] joinTuples(int[][] testArray) {
		Arrays.sort(testArray,
				(Sort, c) -> Integer.compare(Sort[0], c[0]));

		int[][] result = new int[testArray.length][];
		int resultIndex = 0;

		int index = testArray[0][0];
		int current = 0;

		for (int[] tuple : testArray) {
			if (tuple[0] == index) {
				if (current == 0) {
					current = 1 + tuple.length - 1;
					result[resultIndex] = new int[current];
					result[resultIndex][0] = index;
					System.arraycopy(tuple, 1, result[resultIndex], 1, tuple.length - 1);
				} else {
					int oldSize = result[resultIndex].length;
					int old = oldSize + tuple.length - 1;
					result[resultIndex] = Arrays.copyOf(result[resultIndex], old);
					System.arraycopy(tuple, 1, result[resultIndex], oldSize, tuple.length - 1);
				}
			} else {
				index = tuple[0];
				resultIndex++;
				current = 1 + tuple.length - 1;
				result[resultIndex] = new int[current];
				result[resultIndex][0] = index;
				System.arraycopy(tuple, 1, result[resultIndex], 1, tuple.length - 1);
			}
		}

		return Arrays.copyOf(result, resultIndex + 1);
	}
}
