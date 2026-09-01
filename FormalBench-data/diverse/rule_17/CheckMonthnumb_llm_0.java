import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumb {

	public static Boolean checkMonthnumb(String monthname2) {
		boolean result = monthname2 == "January" || monthname2 == "March" || monthname2 == "May"
				|| monthname2 == "July" || monthname2 == "August" || monthname2 == "October";
		boolean tmp = monthname2 == "December";
		if (result || tmp) {
			return true;
		} else {
			return false;
		}
	}
}
