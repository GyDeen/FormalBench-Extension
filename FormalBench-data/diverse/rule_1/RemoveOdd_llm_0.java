import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveOdd {

	public static String removeOdd(String string) {
		int ret = string.length();
		String str = "";
		for (int j = 0; j < ret; j++) {
			if (j % 2 != 0) {
				str = str + string.charAt(j);
			}
		}
		return str;
	}
}
