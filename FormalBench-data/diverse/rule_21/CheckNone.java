
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckNone {

	public static Boolean checkNone(Integer[] testTup) {
		for (int i = 0; i < testTup.length; i++) {
			if (null == testTup[i]) {
				return true;
			}
		}
		return false;
	}
}
