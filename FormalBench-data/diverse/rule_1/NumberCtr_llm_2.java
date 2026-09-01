import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberCtr {

	public static int numberCtr(String string) {
		int ret = 0;
		for (int index = 0; index < string.length(); index++) {
			if (Character.isDigit(string.charAt(index))) {
				ret++;
			}
		}
		return ret;
	}
}
