import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EndNum {

	public static Boolean endNum(String str) {
		if (str.length() == 0) {
			return false;
		}
		int end = str.length() - 1;
		char first = str.charAt(end);
		if (Character.isDigit(first)) {
			return true;
		}
		return false;
	}
}
