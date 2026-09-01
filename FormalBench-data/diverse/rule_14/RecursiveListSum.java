
import java.io.*;
import java.lang.*;
import java.math.*;

class RecursiveArraySum {

	public static int recursiveArraySum(Object[] dataArray) {
		int sum = 0;
		for (Object item : dataArray) {
			if (!(item instanceof Object[])) {
				sum += (Integer) item;
			} else {
				sum += recursiveArraySum((Object[]) item);
			}
		}
		return sum;
	}
}
