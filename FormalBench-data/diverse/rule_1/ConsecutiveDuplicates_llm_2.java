
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConsecutiveDuplicates {

	public static Object[] consecutiveDuplicates(Object[] input) {
		if (input.length == 0) {
			return new Object[0];
		}

		Object[] temp = new Object[input.length];
		int index = 0;

		temp[index++] = input[0];

		for (int j = 1; j < input.length; j++) {
			if (!compare(input[j], input[j - 1])) {
				temp[index++] = input[j];
			}
		}

		Object[] copy = Arrays.copyOf(temp, index);
		return copy;
	}

	public static boolean compare(Object num, Object obj) {
		if (num == null && obj == null) {
			return true;
		} else if (num == null || obj == null) {
			return false;
		} else {
			return num.equals(obj);
		}
	}
}
