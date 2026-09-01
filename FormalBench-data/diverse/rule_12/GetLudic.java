
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetLudic {

	public static int[] getLudic(int n) {
		int count = 0;
		int[] ludics = new int[n];

		int index = 1;

		for (int i = 1; i <= n; i++) {
			ludics[count++] = i;
		}
		while (index < count) {
			int first_ludic = ludics[index];
			int remove_index = index + first_ludic;

			index += 1;
			while (remove_index < count) {
				System.arraycopy(ludics, remove_index + 1, ludics, remove_index, count - remove_index - 1);
				count--;
				remove_index += first_ludic - 1;
			}
		}

		int[] result = Arrays.copyOf(ludics, count);
		return result;
	}
}
