
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxVal {

	public static int maxVal(Object[] arrayval) {
		int max = 0;
		for (Object val : arrayval) {
			max = (val instanceof Integer) ? Math.max((Integer) val, max) : max;
		}
		return max;
	}
}
