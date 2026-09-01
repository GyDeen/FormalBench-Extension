import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecimalToBinary {

	public static int decimalToBinary(int decimal) {
		String Builder = "";
		while (decimal > 0) {
			int i = decimal % 2;
			Builder = i + Builder;
			decimal = decimal / 2;
		}
		return Integer.parseInt(Builder);
	}
}
