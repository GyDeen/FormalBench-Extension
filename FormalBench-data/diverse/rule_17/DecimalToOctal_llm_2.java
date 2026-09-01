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
			int c = rem * (int) Math.pow(10, i);
			octal = octal + c;
			decinum = decinum / 8;
			i++;
		}
		return octal;
	}
}
