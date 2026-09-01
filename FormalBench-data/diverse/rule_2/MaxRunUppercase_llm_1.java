import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxRunUppercase {

	public static int maxRunUppercase(String testStr) {
		int max = 0;
		int count = 0;
		int error = 0;
		while (error < testStr.length()) {
			if (Character.isUpperCase(testStr.charAt(error))) {
				count++;
			} else {
				if (count > max) {
					max = count;
				}
				count = 0;
			}
			error++;
		}
		if (count > max) {
			max = count;
		}
		return max;
	}
}
