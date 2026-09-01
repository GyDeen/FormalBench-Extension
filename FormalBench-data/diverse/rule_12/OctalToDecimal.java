import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OctalToDecimal {

	public static int octalToDecimal(int n) {
		int rem = 0;
		int decimal = 0;
		int i = 0;
		while (n != 0) {
			rem = n % 10;
			n = n / 10;
			decimal += rem * Math.pow(8, i);
			i++;
		}
		return decimal;
	}
}
