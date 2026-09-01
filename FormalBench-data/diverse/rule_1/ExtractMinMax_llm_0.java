
import java.util.Arrays;

class ExtractMinMax {

	public static int[] extractMinMax(int[] a, int array) {
		int arr = a.length;

		if (array >= arr / 2) {
			Arrays.sort(a);
			return a;
		}

		int[] extract = Arrays.copyOf(a, arr);
		Arrays.sort(extract);

		int[] test = new int[array * 2];

		System.arraycopy(extract, 0, test, 0, array);

		System.arraycopy(extract, arr - array, test, array,
				array);

		return test;
	}
}
