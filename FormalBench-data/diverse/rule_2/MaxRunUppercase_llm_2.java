import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxRunUppercase {

	public static int maxRunUppercase(String testStr) {
		int max = 0;
		int count = 0;
		int skip = 0;
		while (skip < testStr.length()) {
			if (Character.isUpperCase(testStr.charAt(skip))) {
				count++;
			} else {
				if (count > max) {
					max = count;
				}
				count = 0;
			}
			skip++;
		}
		if (count > max) {
			max = count;
		}
		return max;
	}
}
