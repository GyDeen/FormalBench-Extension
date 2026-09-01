import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecimalToBinary {

	public static int decimalToBinary(int value) {
		String Builder = "";
		while (value > 0) {
			int i = value % 2;
			Builder = i + Builder;
			value = value / 2;
		}
		return Integer.parseInt(Builder);
	}
}
