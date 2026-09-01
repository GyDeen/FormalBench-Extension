
import java.io.*;
import java.lang.*;
import java.math.*;

class RecursiveArraySum {

	public static int recursiveArraySum(Object[] dataArray) {
		int sum = 0;
		for (Object item : dataArray) {
			if (item instanceof Object[]) {
				sum = sum + (recursiveArraySum((Object[]) item));
			} else {
				sum = sum + ((Integer) item);
			}
		}
		return sum;
	}
}
