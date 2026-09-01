
import java.io.*;
import java.lang.*;
import java.math.*;

class CountInteger {

	public static int countInteger(Object[] objects) {
		int result = 0;
		for (Object object : objects) {
			if (object instanceof Integer) {
				result++;
			}
		}
		return result;
	}
}
