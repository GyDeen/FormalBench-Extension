import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMonthnumbNumber {

	public static Boolean checkMonthnumbNumber(int n) {
		if (n == 1 || n == 3 || n == 5
				|| n == 7 || n == 8 || n == 10
				|| n == 12) {
			return true;
		}
		return false;
	}
}
