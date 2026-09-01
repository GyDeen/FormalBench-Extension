
import java.util.Arrays;

class FindPlatform {

	public static int findPlatform(int[] a, int[] target, int n) {
		Arrays.sort(a);
		Arrays.sort(target);

		int find = 1;
		int result = 1;
		int plat = 1;
		int p = 0;

		while (plat < n && p < n) {
			if (a[plat] <= target[p]) {
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
