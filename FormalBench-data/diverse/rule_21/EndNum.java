import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EndNum {

	public static Boolean endNum(String string) {
		if (0 == string.length()) {
			return false;
		}
		int last = string.length() - 1;
		char lastChar = string.charAt(last);
		if (Character.isDigit(lastChar)) {
			return true;
		}
		return false;
	}
}
