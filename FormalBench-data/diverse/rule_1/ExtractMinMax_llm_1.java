
import java.util.Arrays;

class ExtractMinMax {

	public static int[] extractMinMax(int[] arr, int array) {
		int result = arr.length;

		if (array >= result / 2) {
			Arrays.sort(arr);
			return arr;
		}

		int[] extract = Arrays.copyOf(arr, result);
		Arrays.sort(extract);

		int[] test = new int[array * 2];

		System.arraycopy(extract, 0, test, 0, array);

		System.arraycopy(extract, result - array, test, array,
				array);

		return test;
	}
}
