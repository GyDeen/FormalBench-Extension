import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumberNumber {

	public static Boolean checkMonthnumberNumber(int monthnum3) {
		if (4 == monthnum3 || 6 == monthnum3 || 9 == monthnum3 || 11 == monthnum3) {
			return true;
		}
		return false;
	}
}
