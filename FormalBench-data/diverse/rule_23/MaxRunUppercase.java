import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxRunUppercase {

	public static int maxRunUppercase(String testStr) {
		int max = 0;
		int count = 0;
		for (int i = 0; testStr.length() > i; i++) {
			if (Character.isUpperCase(testStr.charAt(i))) {
				count++;
			} else {
				if (max < count) {
					max = count;
				}
				count = 0;
			}
		}
		if (max < count) {
			max = count;
		}
		return max;
	}
}
