
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckNone {

	public static Boolean checkNone(Integer[] testTup) {
		int result = 0;
		while (result < testTup.length) {
			if (testTup[result] == null) {
				return true;
			}
			result++;
		}
		return false;
	}
}
