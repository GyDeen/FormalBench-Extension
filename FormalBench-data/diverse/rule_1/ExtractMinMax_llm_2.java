
import java.util.Arrays;

class ExtractMinMax {

	public static int[] extractMinMax(int[] a, int Array) {
		int arr = a.length;

		if (Array >= arr / 2) {
			Arrays.sort(a);
			return a;
		}

		int[] extract = Arrays.copyOf(a, arr);
		Arrays.sort(extract);

		int[] test = new int[Array * 2];

		System.arraycopy(extract, 0, test, 0, Array);

		System.arraycopy(extract, arr - Array, test, Array,
				Array);

		return test;
	}
}
