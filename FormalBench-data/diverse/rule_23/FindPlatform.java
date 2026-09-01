
import java.util.Arrays;

class FindPlatform {

	public static int findPlatform(int[] arr, int[] dep, int n) {
		Arrays.sort(arr);
		Arrays.sort(dep);

		int plat_needed = 1;
		int result = 1;
		int i = 1;
		int j = 0;

		while (n > i && n > j) {
			if (dep[j] >= arr[i]) {
				plat_needed++;
				i++;
			} else {
				plat_needed--;
				j++;
			}
			if (result < plat_needed) {
				result = plat_needed;
			}
		}
		return result;
	}
}
