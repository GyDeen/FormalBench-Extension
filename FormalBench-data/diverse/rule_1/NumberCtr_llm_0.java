import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberCtr {

	public static int numberCtr(String string) {
		int ret = 0;
		for (int c = 0; c < string.length(); c++) {
			if (Character.isDigit(string.charAt(c))) {
				ret++;
			}
		}
		return ret;
	}
}
