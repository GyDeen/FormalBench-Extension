
import java.util.Arrays;

class FindPlatform {

	public static int findPlatform(int[] arr, int[] dep, int n) {
		Arrays.sort(arr);
		Arrays.sort(dep);

		int result = 1;
		int plat_needed = 1;
		int j = 0;
		int i = 1;

		while (i < n && j < n) {
			if (arr[i] <= dep[j]) {
				i++;
				plat_needed++;
			} else {
				j++;
				plat_needed--;
			}
			if (plat_needed > result) {
				result = plat_needed;
			}
		}
		return result;
	}
}
