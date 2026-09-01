import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumberNumber {

	public static Boolean checkMonthnumberNumber(int monthnum3) {
		boolean value = monthnum3 == 4 || monthnum3 == 6 || monthnum3 == 9;
		boolean tmp = monthnum3 == 11;
		if (value || tmp) {
			return true;
		}
		return false;
	}
}
