
import java.io.*;
import java.lang.*;
import java.math.*;

class IsProductEven {

	public static Boolean isProductEven(int[] products, int array) {
		int ret = 1;
		for (int index = 0; index < products.length; index++) {
			ret *= products[index];
		}
		return ret % array == 0;
	}
}
