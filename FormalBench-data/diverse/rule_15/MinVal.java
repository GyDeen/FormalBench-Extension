
import java.io.*;
import java.lang.*;
import java.math.*;

class MinVal {

	public static int minVal(Object[] arrayval) {
		int min = Integer.MAX_VALUE;
		for (Object val : arrayval) {
			min = (val instanceof Integer) ? Math.min(min, (Integer) val) : min;
		}
		return min;
	}
}
