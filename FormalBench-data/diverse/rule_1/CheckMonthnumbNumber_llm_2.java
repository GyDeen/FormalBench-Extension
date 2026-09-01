import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumbNumber {

	public static Boolean checkMonthnumbNumber(int num) {
		if (num == 1 || num == 3 || num == 5
				|| num == 7 || num == 8 || num == 10
				|| num == 12) {
			return true;
		}
		return false;
	}
}
