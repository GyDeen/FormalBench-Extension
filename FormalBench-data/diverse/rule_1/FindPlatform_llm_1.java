
import java.util.Arrays;

class FindPlatform {

	public static int findPlatform(int[] c, int[] platform, int n) {
		Arrays.sort(c);
		Arrays.sort(platform);

		int find = 1;
		int result = 1;
		int plat = 1;
		int p = 0;

		while (plat < n && p < n) {
			if (c[plat] <= platform[p]) {
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
