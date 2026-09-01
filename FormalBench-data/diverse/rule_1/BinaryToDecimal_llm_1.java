import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinaryToDecimal {

	public static int binaryToDecimal(int value) {
		int result = 0;
		int dec = 0;
		while (value != 0) {
			int i = value % 10;
			result += i * Math.pow(2, dec);
			value /= 10;
			dec++;
		}
		return result;
	}
}
