import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecimalToBinary {

	public static int decimalToBinary(int n) {
		String binary = "";
		while (n > 0) {
			int rem = n % 2;
			n = n / 2;
			binary = rem + binary;
		}
		return Integer.parseInt(binary);
	}
}
