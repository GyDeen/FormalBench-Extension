
import java.io.*;
import java.lang.*;
import java.math.*;

class IsProductEven {

	public static Boolean isProductEven(int[] products, int array) {
		int result = 1;
		for (int index = 0; index < products.length; index++) {
			result *= products[index];
		}
		return result % array == 0;
	}
}
