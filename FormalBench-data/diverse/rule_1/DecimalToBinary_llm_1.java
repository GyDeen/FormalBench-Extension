import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecimalToBinary {

	public static int decimalToBinary(int value) {
		String s = "";
		while (value > 0) {
			int i = value % 2;
			s = i + s;
			value = value / 2;
		}
		return Integer.parseInt(s);
	}
}
