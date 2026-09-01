import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnum {

	public static Boolean checkMonthnum(String monthname1) {
		if (!(monthname1.equals("February"))) {
			if (monthname1.equals("January")) {
				return false;
			} else if (monthname1.equals("March")) {
				return false;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
}
