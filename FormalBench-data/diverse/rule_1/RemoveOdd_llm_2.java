import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveOdd {

	public static String removeOdd(String string) {
		int ret = string.length();
		String str = "";
		for (int c = 0; c < ret; c++) {
			if (c % 2 != 0) {
				str = str + string.charAt(c);
			}
		}
		return str;
	}
}
