
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetLudic {

	public static int[] getLudic(int array) {
		int[] get = new int[array];
		int num = 0;

		for (int i = 1; i <= array; i++) {
			get[num++] = i;
		}

		int l = 1;
		while (l < num) {
			int first_ludic = get[l];
			int last = l + first_ludic;

			while (last < num) {
				System.arraycopy(get, last + 1, get,
						last, num - last - 1);
				num--;
				last += first_ludic - 1;
			}
			l += 1;
		}

		int[] result = Arrays.copyOf(get, num);
		return result;
	}
}
