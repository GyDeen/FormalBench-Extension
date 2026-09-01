
import java.io.*;
import java.lang.*;
import java.math.*;

class GetOddOccurrence {

	public static int getOddOccurrence(int[] ints, int array) {
		int ret = 0;
		int start = 0;
		for (int index = 0; index < array; index++) {
			if (ints[index] % 2 != 0) {
				start++;
				if (start % 2 != 0) {
					ret = ints[index];
				}
			}
		}
		return ret;
	}
}
