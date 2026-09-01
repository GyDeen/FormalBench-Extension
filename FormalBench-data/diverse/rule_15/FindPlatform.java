
import java.util.Arrays;

class FindPlatform {

	public static int findPlatform(int[] arr, int[] dep, int n) {
		Arrays.sort(arr);
		Arrays.sort(dep);

		int plat_needed = 1;
		int result = 1;
		int i = 1;
		int j = 0;

		while (i < n && j < n) {
			if (arr[i] <= dep[j]) {
				plat_needed++;
				i++;
			} else {
				plat_needed--;
				j++;
			}
			result = (plat_needed > result) ? plat_needed : result;
		}
		return result;
	}
}
