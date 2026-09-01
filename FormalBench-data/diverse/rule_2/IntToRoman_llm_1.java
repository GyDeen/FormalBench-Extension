import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IntToRoman {

	public static String intToRoman(int num) {
		String[] roman = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		StringBuilder sb = new StringBuilder();
		int arr = 0;
		while (arr < values.length) {
			while (num >= values[arr]) {
				num -= values[arr];
				sb.append(roman[arr]);
			}
			arr++;
		}
		return sb.toString();
	}
}
