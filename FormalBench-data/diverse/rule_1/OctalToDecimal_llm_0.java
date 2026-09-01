import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OctalToDecimal {

	public static int octalToDecimal(int value) {
		int result = 0;
		int dec = 0;
		int sign = 0;
		while (value != 0) {
			dec = value % 10;
			result += dec * Math.pow(8, sign);
			value = value / 10;
			sign++;
		}
		return result;
	}
}
