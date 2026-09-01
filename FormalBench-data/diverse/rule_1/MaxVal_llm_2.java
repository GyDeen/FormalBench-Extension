
import java.io.*;
import java.lang.*;
import java.math.*;

class MaxVal {

	public static int maxVal(Object[] objects) {
		int result = 0;
		for (Object object : objects) {
			if (object instanceof Integer) {
				result = Math.max((Integer) object, result);
			}
		}
		return result;
	}
}
