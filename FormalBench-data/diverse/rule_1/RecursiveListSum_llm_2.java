
import java.io.*;
import java.lang.*;
import java.math.*;

class RecursiveArraySum {

	public static int recursiveArraySum(Object[] objects) {
		int result = 0;
		for (Object data : objects) {
			if (data instanceof Object[]) {
				result += recursiveArraySum((Object[]) data);
			} else {
				result += (Integer) data;
			}
		}
		return result;
	}
}
