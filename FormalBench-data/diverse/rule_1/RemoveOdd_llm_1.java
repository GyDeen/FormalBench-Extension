import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveOdd {

	public static String removeOdd(String string) {
		int ret = string.length();
		String Str = "";
		for (int j = 0; j < ret; j++) {
			if (j % 2 != 0) {
				Str = Str + string.charAt(j);
			}
		}
		return Str;
	}
}
