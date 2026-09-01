import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LastTwoDigits {

	public static int lastTwoDigits(int n) {
		int factorial = 1;
		int even = 1;
		while (even <= n) {
			factorial *= even;
			even++;
		}
		int lastTwoDigits = (int) (factorial % 100);
		return lastTwoDigits;
	}
}
