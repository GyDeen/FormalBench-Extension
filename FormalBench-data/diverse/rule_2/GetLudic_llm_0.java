
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetLudic {

	public static int[] getLudic(int n) {
		int[] ludics = new int[n];
		int count = 0;

		int start = 1;
		while (start <= n) {
			ludics[count++] = start;
			start++;
		}

		int index = 1;
		while (index < count) {
			int first_ludic = ludics[index];
			int remove_index = index + first_ludic;

			while (remove_index < count) {
				System.arraycopy(ludics, remove_index + 1, ludics, remove_index, count - remove_index - 1);
				count--;
				remove_index += first_ludic - 1;
			}
			index += 1;
		}

		int[] result = Arrays.copyOf(ludics, count);
		return result;
	}
}
