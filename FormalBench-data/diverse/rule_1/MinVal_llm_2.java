
import java.io.*;
import java.lang.*;
import java.math.*;

class MinVal {

	public static int minVal(Object[] objects) {
		int max = Integer.MAX_VALUE;
		for (Object object : objects) {
			if (object instanceof Integer) {
				max = Math.min(max, (Integer) object);
			}
		}
		return max;
	}
}
