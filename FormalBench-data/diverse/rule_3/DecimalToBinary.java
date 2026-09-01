import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecimalToBinary {

	public static int decimalToBinary(int n) {
		String binary = "";
		for (; n > 0;) {
			int rem = n % 2;
			binary = rem + binary;
			n = n / 2;
		}
		return Integer.parseInt(binary);
	}
}
