import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumber {

	public static Boolean checkMonthnumber(String str) {
		if (str.equals("April") || str.equals("June")
				|| str.equals("September") || str.equals("November")) {
			return true;
		} else {
			return false;
		}
	}
}
