
import java.io.*;
import java.lang.*;
import java.math.*;

class CountInteger {

	public static int countInteger(Object[] array) {
		int count = 0;
		for (Object obj : array) {
			if (obj instanceof Integer) {
				count += 1;
			}
		}
		return count;
	}
}
