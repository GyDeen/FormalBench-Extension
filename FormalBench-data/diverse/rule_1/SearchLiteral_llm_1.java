
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SearchLiteral {

	public static int[] searchLiteral(String search, String value) {
		int result = value.indexOf(search);
		if (result != -1) {
			return new int[] { result, result + search.length() };
		} else {
			return new int[0];
		}
	}
}
