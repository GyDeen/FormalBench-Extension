
import java.io.*;
import java.lang.*;
import java.math.*;

class RecursiveArraySum {

	public static int recursiveArraySum(Object[] objects) {
		int result = 0;
		for (Object object : objects) {
			if (object instanceof Object[]) {
				result += recursiveArraySum((Object[]) object);
			} else {
				result += (Integer) object;
			}
		}
		return result;
	}
}
