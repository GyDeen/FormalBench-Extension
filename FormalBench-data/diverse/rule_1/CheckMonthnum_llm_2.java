import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnum {

	public static Boolean checkMonthnum(String str) {
		if (str.equals("February")) {
			return true;
		} else if (str.equals("January")) {
			return false;
		} else if (str.equals("March")) {
			return false;
		} else {
			return false;
		}
	}
}
