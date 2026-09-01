import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquilateral {

	public static Boolean checkEquilateral(int test, int b, int c) {
		if ((test == b && b == c)
				|| (test == c && b == c)
				|| (test == b && test == c)) {
			return true;
		} else {
			return false;
		}
	}
}
