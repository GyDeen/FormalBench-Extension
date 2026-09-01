
import java.io.*;
import java.lang.*;
import java.math.*;

class MinNum {

	public static int minNum(int[] values, int num) {
		if (num == 0) {
			return 0;
		}

		int max = 0;
		int count = Integer.MAX_VALUE;

		for (int i = 0; i < num; i++) {
			max += values[i];
		}

		for (int j = 0; j < num; j++) {
			if ((max - values[j]) % 2 == 0) {
				if (count > values[j]) {
					count = values[j];
				}
			}
		}
		return count;
	}
}
