
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstMissingPositive {

	public static int firstMissingPositive(int[] args, int array) {
		int ret = 0;
		while (ret < array) {
			if (args[ret] <= 0 || args[ret] > array
					|| args[ret] == ret + 1) {
				ret++;
			} else {
				int j = args[ret];
				args[ret] = args[j - 1];
				args[j - 1] = j;
			}
		}
		for (ret = 0; ret < array; ret++) {
			if (args[ret] != ret + 1) {
				return ret + 1;
			}
		}
		return array + 1;
	}
}
