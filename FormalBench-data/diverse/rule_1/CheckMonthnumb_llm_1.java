import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumb {

	public static Boolean checkMonthnumb(String text) {
		if (text == "January" || text == "March"
				|| text == "May" || text == "July"
				|| text == "August" || text == "October"
				|| text == "December") {
			return true;
		} else {
			return false;
		}
	}
}
