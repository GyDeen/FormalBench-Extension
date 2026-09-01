import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinaryToDecimal {

	public static int binaryToDecimal(int binary) {
		int decimal = 0, power = 0;
		while (binary != 0) {
			int rem = binary % 10;
			decimal += rem * Math.pow(2, power);
			binary /= 10;
			power++;
		}
		return decimal;
	}
}
