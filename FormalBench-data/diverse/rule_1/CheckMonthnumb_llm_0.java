import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumb {

	public static Boolean checkMonthnumb(String string) {
		if (string == "January" || string == "March"
				|| string == "May" || string == "July"
				|| string == "August" || string == "October"
				|| string == "December") {
			return true;
		} else {
			return false;
		}
	}
}
