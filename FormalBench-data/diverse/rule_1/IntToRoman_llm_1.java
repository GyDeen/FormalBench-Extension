import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IntToRoman {

	public static String intToRoman(int value) {
		String[] string = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] v = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		StringBuilder builder = new StringBuilder();
		for (int val = 0; val < v.length; val++) {
			while (value >= v[val]) {
				value -= v[val];
				builder.append(string[val]);
			}
		}
		return builder.toString();
	}
}
