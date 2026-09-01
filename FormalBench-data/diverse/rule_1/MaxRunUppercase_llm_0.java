import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxRunUppercase {

	public static int maxRunUppercase(String string) {
		int test = 0;
		int min = 0;
		for (int index = 0; index < string.length(); index++) {
			if (Character.isUpperCase(string.charAt(index))) {
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
