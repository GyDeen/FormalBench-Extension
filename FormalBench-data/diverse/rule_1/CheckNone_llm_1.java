
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckNone {

	public static Boolean checkNone(Integer[] checks) {
		for (int test = 0; test < checks.length; test++) {
			if (checks[test] == null) {
				return true;
			}
		}
		return false;
	}
}
