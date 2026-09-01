import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxRunUppercase {

	public static int maxRunUppercase(String testStr) {
		int max = 0;
		int count = 0;
		for (int i = 0; i < testStr.length(); i++) {
			if (Character.isUpperCase(testStr.charAt(i))) {
				count++;
			} else {
				max = (count > max) ? count : max;
				count = 0;
			}
		}
		max = (count > max) ? count : max;
		return max;
	}
}
