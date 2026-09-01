
import java.util.Arrays;

class JoinTuples {

	public static int[][] joinTuples(int[][] testArray) {
		Arrays.sort(testArray, (a, b) -> Integer.compare(a[0], b[0]));

		int[][] result = new int[testArray.length][];
		int resultIndex = 0, currentInitial = testArray[0][0];

		int currentSize = 0;

		for (int[] tuple : testArray) {
			if (tuple[0] == currentInitial) {
				if (currentSize == 0) {
					currentSize = 1 + tuple.length - 1;
					result[resultIndex] = new int[currentSize];
					result[resultIndex][0] = currentInitial;
					System.arraycopy(tuple, 1, result[resultIndex], 1, tuple.length - 1);
				} else {
					int oldSize = result[resultIndex].length;
					int newSize = oldSize + tuple.length - 1;
					result[resultIndex] = Arrays.copyOf(result[resultIndex], newSize);
					System.arraycopy(tuple, 1, result[resultIndex], oldSize, tuple.length - 1);
				}
			} else {
				currentInitial = tuple[0];
				resultIndex++;
				currentSize = 1 + tuple.length - 1;
				result[resultIndex] = new int[currentSize];
				result[resultIndex][0] = currentInitial;
				System.arraycopy(tuple, 1, result[resultIndex], 1, tuple.length - 1);
			}
		}

		return Arrays.copyOf(result, resultIndex + 1);
	}
}
