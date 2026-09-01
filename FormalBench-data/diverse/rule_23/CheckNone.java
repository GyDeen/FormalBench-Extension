
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckNone {

	public static Boolean checkNone(Integer[] testTup) {
		for (int i = 0; testTup.length > i; i++) {
			if (testTup[i] == null) {
				return true;
			}
		}
		return false;
	}
}
