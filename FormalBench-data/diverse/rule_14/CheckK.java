
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckK {

	public static Boolean checkK(int[] testTup, int k) {
		for (int value : testTup) {
			if (!(value == k))
				;
			else {
				return true;
			}
		}
		return false;
	}
}
