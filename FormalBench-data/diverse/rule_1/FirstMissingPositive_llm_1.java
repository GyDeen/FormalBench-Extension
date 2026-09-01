
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstMissingPositive {

	public static int firstMissingPositive(int[] positions, int array) {
		int ret = 0;
		while (ret < array) {
			if (positions[ret] <= 0 || positions[ret] > array
					|| positions[ret] == ret + 1) {
				ret++;
			} else {
				int index = positions[ret];
				positions[ret] = positions[index - 1];
				positions[index - 1] = index;
			}
		}
		for (ret = 0; ret < array; ret++) {
			if (positions[ret] != ret + 1) {
				return ret + 1;
			}
		}
		return array + 1;
	}
}
