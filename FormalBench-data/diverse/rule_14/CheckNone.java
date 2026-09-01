
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckNone {

	public static Boolean checkNone(Integer[] testTup) {
		for (int i = 0; i < testTup.length; i++) {
			if (!(testTup[i] == null))
				;
			else {
				return true;
			}
		}
		return false;
	}
}
