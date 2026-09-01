import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckValidity {

	public static Boolean checkValidity(int test, int n, int d) {
		if (test + n > d && test + d > n
				&& n + d > test) {
			return true;
		} else {
			return false;
		}
	}
}
