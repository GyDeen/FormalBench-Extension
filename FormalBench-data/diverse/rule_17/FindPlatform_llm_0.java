
import java.util.Arrays;

class FindPlatform {

	public static int findPlatform(int[] arr, int[] dep, int n) {
		Arrays.sort(arr);
		Arrays.sort(dep);

		int plat_needed = 1;
		int result = 1;
		int i = 1;
		int j = 0;

		boolean platform = i < n;
		boolean tmp = j < n;
		while (platform && tmp) {
			if (arr[i] <= dep[j]) {
				plat_needed++;
				i++;
			} else {
				plat_needed--;
				j++;
			}
			if (plat_needed > result) {
				result = plat_needed;
			}
		}
		return result;
	}
}
