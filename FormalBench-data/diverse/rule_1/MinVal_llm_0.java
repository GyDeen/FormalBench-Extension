
import java.io.*;
import java.lang.*;
import java.math.*;

class MinVal {

	public static int minVal(Object[] objects) {
		int max = Integer.MAX_VALUE;
		for (Object array : objects) {
			if (array instanceof Integer) {
				max = Math.min(max, (Integer) array);
			}
		}
		return max;
	}
}
