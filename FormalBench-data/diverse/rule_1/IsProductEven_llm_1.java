
import java.io.*;
import java.lang.*;
import java.math.*;

class IsProductEven {

	public static Boolean isProductEven(int[] values, int array) {
		int result = 1;
		for (int index = 0; index < values.length; index++) {
			result *= values[index];
		}
		return result % array == 0;
	}
}
