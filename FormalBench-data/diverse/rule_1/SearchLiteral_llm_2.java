
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SearchLiteral {

	public static int[] searchLiteral(String string, String value) {
		int ret = value.indexOf(string);
		if (ret != -1) {
			return new int[] { ret, ret + string.length() };
		} else {
			return new int[0];
		}
	}
}
