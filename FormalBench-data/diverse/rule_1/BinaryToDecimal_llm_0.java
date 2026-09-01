import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinaryToDecimal {

	public static int binaryToDecimal(int b) {
		int result = 0;
		int dec = 0;
		while (b != 0) {
			int i = b % 10;
			result += i * Math.pow(2, dec);
			b /= 10;
			dec++;
		}
		return result;
	}
}
