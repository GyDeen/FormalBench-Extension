import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxRunUppercase {

	public static int maxRunUppercase(String string) {
		int test = 0;
		int min = 0;
		for (int c = 0; c < string.length(); c++) {
			if (Character.isUpperCase(string.charAt(c))) {
				min++;
			} else {
				if (min > test) {
					test = min;
				}
				min = 0;
			}
		}
		if (min > test) {
			test = min;
		}
		return test;
	}
}
