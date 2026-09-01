import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckVow {

	public static int checkVow(String input, String value) {
		int ret = 0;
		for (int loop = 0; loop < input.length(); loop++) {
			char character = input.charAt(loop);
			if (value.indexOf(character) != -1) {
				ret++;
			}
		}
		return ret;
	}
}
