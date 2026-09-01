import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IntToRoman {

	public static String intToRoman(int num) {
		String[] roman = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		StringBuilder sb = new StringBuilder();
		int s = 0;
		while (s < values.length) {
			while (num >= values[s]) {
				num -= values[s];
				sb.append(roman[s]);
			}
			s++;
		}
		return sb.toString();
	}
}
