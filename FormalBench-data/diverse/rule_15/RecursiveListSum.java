
import java.io.*;
import java.lang.*;
import java.math.*;

class RecursiveArraySum {

	public static int recursiveArraySum(Object[] dataArray) {
		int sum = 0;
		for (Object item : dataArray) {
			sum = (item instanceof Object[]) ? recursiveArraySum((Object[]) item) : (Integer) item;
		}
		return sum;
	}
}
