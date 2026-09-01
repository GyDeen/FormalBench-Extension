
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxVal {

	public static int maxVal(Object[] args) {
		int result = 0;
		for (Object array : args) {
			if (array instanceof Integer) {
				result = Math.max((Integer) array, result);
			}
		}
		return result;
	}
}
