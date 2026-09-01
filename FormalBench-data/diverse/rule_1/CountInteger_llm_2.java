
import java.io.*;
import java.lang.*;
import java.math.*;

class CountInteger {

	public static int countInteger(Object[] objects) {
		int result = 0;
		for (Object o : objects) {
			if (o instanceof Integer) {
				result++;
			}
		}
		return result;
	}
}
