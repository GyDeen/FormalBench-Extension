
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SearchLiteral {

	public static int[] searchLiteral(String string, String value) {
		int result = value.indexOf(string);
		if (result != -1) {
			return new int[] { result, result + string.length() };
		} else {
			return new int[0];
		}
	}
}
