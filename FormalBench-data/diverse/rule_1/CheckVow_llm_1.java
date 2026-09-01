import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckVow {

	public static int checkVow(String input, String value) {
		int result = 0;
		for (int loop = 0; loop < input.length(); loop++) {
			char c = input.charAt(loop);
			if (value.indexOf(c) != -1) {
				result++;
			}
		}
		return result;
	}
}
