import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumber {

	public static Boolean checkMonthnumber(String string) {
		if (string.equals("April") || string.equals("June")
				|| string.equals("September") || string.equals("November")) {
			return true;
		} else {
			return false;
		}
	}
}
