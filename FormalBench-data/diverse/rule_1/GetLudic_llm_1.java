
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetLudic {

	public static int[] getLudic(int array) {
		int[] get = new int[array];
		int arr = 0;

		for (int i = 1; i <= array; i++) {
			get[arr++] = i;
		}

		int l = 1;
		while (l < arr) {
			int first_ludic = get[l];
			int rest = l + first_ludic;

			while (rest < arr) {
				System.arraycopy(get, rest + 1, get,
						rest, arr - rest - 1);
				arr--;
				rest += first_ludic - 1;
			}
			l += 1;
		}

		int[] result = Arrays.copyOf(get, arr);
		return result;
	}
}
