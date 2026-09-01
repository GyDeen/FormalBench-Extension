import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumber {

	public static Boolean checkMonthnumber(String monthname3) {
		if ("April".equals(monthname3) || "June".equals(monthname3) || "September".equals(monthname3)
				|| "November".equals(monthname3)) {
			return true;
		} else {
			return false;
		}
	}
}
