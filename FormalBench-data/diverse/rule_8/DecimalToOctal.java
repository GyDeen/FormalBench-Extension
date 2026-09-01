import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecimalToOctal {

	public static int decimalToOctal(int decinum) {
		int octal = 0;
		int rem = 0;
		int i = 0;
		while (decinum != 0) {
			rem = decinum % 8;
			octal = octal + rem * (int) Math.pow(10, i);
			decinum = decinum / 8;
			i += 1;
		}
		return octal;
	}
}
