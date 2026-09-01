import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumbNumber {

	public static Boolean checkMonthnumbNumber(int monthnum2) {
		boolean check = monthnum2 == 1 || monthnum2 == 3 || monthnum2 == 5 || monthnum2 == 7
				|| monthnum2 == 8 || monthnum2 == 10;
		boolean tmp = monthnum2 == 12;
		if (check || tmp) {
			return true;
		}
		return false;
	}
}
