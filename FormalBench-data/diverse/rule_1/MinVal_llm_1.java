
import java.io.*;
import java.lang.*;
import java.math.*;

class MinVal {

	public static int minVal(Object[] args) {
		int max = Integer.MAX_VALUE;
		for (Object array : args) {
			if (array instanceof Integer) {
				max = Math.min(max, (Integer) array);
			}
		}
		return max;
	}
}
