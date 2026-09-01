
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class FloatSort {

	public static String[][] floatSort(String[][] price) {
		if (0 == price.length) {
			return price;
		}

		Arrays.sort(price, (a, b) -> Float.compare(Float.parseFloat(b[1]), Float.parseFloat(a[1])));

		return price;
	}
}
