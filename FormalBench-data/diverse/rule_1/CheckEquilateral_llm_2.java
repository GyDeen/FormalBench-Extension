import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquilateral {

	public static Boolean checkEquilateral(int n, int b, int Z) {
		if ((n == b && b == Z)
				|| (n == Z && b == Z)
				|| (n == b && n == Z)) {
			return true;
		} else {
			return false;
		}
	}
}
