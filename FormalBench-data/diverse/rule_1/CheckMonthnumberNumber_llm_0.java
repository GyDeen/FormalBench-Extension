import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumberNumber {

	public static Boolean checkMonthnumberNumber(int month) {
		if (month == 4 || month == 6 || month == 9
				|| month == 11) {
			return true;
		}
		return false;
	}
}
