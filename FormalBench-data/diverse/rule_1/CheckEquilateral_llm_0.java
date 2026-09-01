import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquilateral {

	public static Boolean checkEquilateral(int n, int b, int c) {
		if ((n == b && b == c)
				|| (n == c && b == c)
				|| (n == b && n == c)) {
			return true;
		} else {
			return false;
		}
	}
}
