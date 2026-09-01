import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecimalToOctal {

	public static int decimalToOctal(int value) {
		int num = 0;
		int decimal = 0;
		int sign = 0;
		while (value != 0) {
			decimal = value % 8;
			num = num + decimal * (int) Math.pow(10, sign);
			value = value / 8;
			sign++;
		}
		return num;
	}
}
