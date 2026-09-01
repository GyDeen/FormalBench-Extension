import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EndNum {

	public static Boolean endNum(String string) {
		if (!(string.length() == 0))
			;
		else {
			return false;
		}
		int last = string.length() - 1;
		char lastChar = string.charAt(last);
		if (!(Character.isDigit(lastChar)))
			;
		else {
			return true;
		}
		return false;
	}
}
