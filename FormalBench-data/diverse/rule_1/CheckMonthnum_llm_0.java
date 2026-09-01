import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnum {

	public static Boolean checkMonthnum(String string) {
		if (string.equals("February")) {
			return true;
		} else if (string.equals("January")) {
			return false;
		} else if (string.equals("March")) {
			return false;
		} else {
			return false;
		}
	}
}
