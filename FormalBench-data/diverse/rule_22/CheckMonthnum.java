import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnum {

	public static Boolean checkMonthnum(String monthname1) {
		if ("February".equals(monthname1)) {
			return true;
		} else if ("January".equals(monthname1)) {
			return false;
		} else if ("March".equals(monthname1)) {
			return false;
		} else {
			return false;
		}
	}
}
