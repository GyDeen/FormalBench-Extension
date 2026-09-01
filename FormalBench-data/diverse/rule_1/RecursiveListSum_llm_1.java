
import java.io.*;
import java.lang.*;
import java.math.*;

class RecursiveArraySum {

	public static int recursiveArraySum(Object[] array) {
		int result = 0;
		for (Object object : array) {
			if (object instanceof Object[]) {
				result += recursiveArraySum((Object[]) object);
			} else {
				result += (Integer) object;
			}
		}
		return result;
	}
}
