import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinaryToDecimal {

	public static int binaryToDecimal(int binary) {
		int power = 0;
		int decimal = 0;
		while (binary != 0) {
			int rem = binary % 10;
			binary /= 10;
			decimal += rem * Math.pow(2, power);
			power++;
		}
		return decimal;
	}
}
