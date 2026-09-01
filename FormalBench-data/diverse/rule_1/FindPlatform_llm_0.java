
import java.util.Arrays;

class FindPlatform {

	public static int findPlatform(int[] a, int[] platform, int n) {
		Arrays.sort(a);
		Arrays.sort(platform);

		int find = 1;
		int result = 1;
		int plat = 1;
		int p = 0;

		while (plat < n && p < n) {
			if (a[plat] <= platform[p]) {
				find++;
				plat++;
			} else {
				find--;
				p++;
			}
			if (find > result) {
				result = find;
			}
		}
		return result;
	}
}
