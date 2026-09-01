import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberCtr {

	public static int numberCtr(String input) {
		int ret = 0;
		for (int c = 0; c < input.length(); c++) {
			if (Character.isDigit(input.charAt(c))) {
				ret++;
			}
		}
		return ret;
	}
}
