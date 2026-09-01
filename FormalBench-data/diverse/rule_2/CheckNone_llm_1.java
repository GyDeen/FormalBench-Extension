
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckNone {

	public static Boolean checkNone(Integer[] testTup) {
		int i = 0;
		while (i < testTup.length) {
			if (testTup[i] == null) {
				return true;
			}
			i++;
		}
		return false;
	}
}
