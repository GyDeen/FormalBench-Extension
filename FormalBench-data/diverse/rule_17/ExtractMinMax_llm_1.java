
import java.util.Arrays;

class ExtractMinMax {

	public static int[] extractMinMax(int[] testTup, int k) {
		int n = testTup.length;

		int max = n / 2;
		if (k >= max) {
			Arrays.sort(testTup);
			return testTup;
		}

		int[] temp = Arrays.copyOf(testTup, n);
		Arrays.sort(temp);

		int[] res = new int[k * 2];

		System.arraycopy(temp, 0, res, 0, k);

		System.arraycopy(temp, n - k, res, k, k);

		return res;
	}
}
