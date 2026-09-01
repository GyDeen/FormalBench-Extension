import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumbNumber {

	public static Boolean checkMonthnumbNumber(int month) {
		if (month == 1 || month == 3 || month == 5
				|| month == 7 || month == 8 || month == 10
				|| month == 12) {
			return true;
		}
		return false;
	}
}
