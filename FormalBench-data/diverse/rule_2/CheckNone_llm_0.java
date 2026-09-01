
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckNone {

	public static Boolean checkNone(Integer[] testTup) {
		int test = 0;
		while (test < testTup.length) {
			if (testTup[test] == null) {
				return true;
			}
			test++;
		}
		return false;
	}
}
