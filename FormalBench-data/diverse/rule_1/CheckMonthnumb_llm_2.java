import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumb {

	public static Boolean checkMonthnumb(String str) {
		if (str == "January" || str == "March"
				|| str == "May" || str == "July"
				|| str == "August" || str == "October"
				|| str == "December") {
			return true;
		} else {
			return false;
		}
	}
}
