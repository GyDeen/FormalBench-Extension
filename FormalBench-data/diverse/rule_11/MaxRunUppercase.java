import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxRunUppercase {

	public static int maxRunUppercase(String testStr) {
		int max = 0, count = 0;
		for (int i = 0; i < testStr.length(); i++) {
			if (Character.isUpperCase(testStr.charAt(i))) {
				count++;
			} else {
				if (count > max) {
					max = count;
				}
				count = 0;
			}
		}
		if (count > max) {
			max = count;
		}
		return max;
	}
}
