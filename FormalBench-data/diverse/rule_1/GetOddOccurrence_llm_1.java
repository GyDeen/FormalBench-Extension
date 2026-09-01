
import java.io.*;
import java.lang.*;
import java.math.*;

class GetOddOccurrence {

	public static int getOddOccurrence(int[] array, int Array) {
		int ret = 0;
		int start = 0;
		for (int index = 0; index < Array; index++) {
			if (array[index] % 2 != 0) {
				start++;
				if (start % 2 != 0) {
					ret = array[index];
				}
			}
		}
		return ret;
	}
}
