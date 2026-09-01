
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxVal {

	public static int maxVal(Object[] objects) {
		int result = 0;
		for (Object array : objects) {
			if (array instanceof Integer) {
				result = Math.max((Integer) array, result);
			}
		}
		return result;
	}
}
