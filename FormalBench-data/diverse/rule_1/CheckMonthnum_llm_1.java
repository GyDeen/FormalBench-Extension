import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnum {

	public static Boolean checkMonthnum(String input) {
		if (input.equals("February")) {
			return true;
		} else if (input.equals("January")) {
			return false;
		} else if (input.equals("March")) {
			return false;
		} else {
			return false;
		}
	}
}
