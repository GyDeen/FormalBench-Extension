import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxRunUppercase {

	public static int maxRunUppercase(String testStr) {
		int max = 0;
		int count = 0;
		int min = 0;
		while (min < testStr.length()) {
			if (Character.isUpperCase(testStr.charAt(min))) {
				count++;
			} else {
				if (count > max) {
					max = count;
				}
				count = 0;
			}
			min++;
		}
		if (count > max) {
			max = count;
		}
		return max;
	}
}
