import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumb {

	public static Boolean checkMonthnumb(String monthname2) {
		if ("January" == monthname2 || "March" == monthname2 || "May" == monthname2 || "July" == monthname2
				|| "August" == monthname2 || "October" == monthname2 || "December" == monthname2) {
			return true;
		} else {
			return false;
		}
	}
}
