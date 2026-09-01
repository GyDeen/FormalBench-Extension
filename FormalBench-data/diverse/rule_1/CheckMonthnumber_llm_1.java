import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumber {

	public static Boolean checkMonthnumber(String input) {
		if (input.equals("April") || input.equals("June")
				|| input.equals("September") || input.equals("November")) {
			return true;
		} else {
			return false;
		}
	}
}
