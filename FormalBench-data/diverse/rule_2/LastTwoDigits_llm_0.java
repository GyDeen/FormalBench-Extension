import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LastTwoDigits {

	public static int lastTwoDigits(int n) {
		int factorial = 1;
		int fraction = 1;
		while (fraction <= n) {
			factorial *= fraction;
			fraction++;
		}
		int lastTwoDigits = (int) (factorial % 100);
		return lastTwoDigits;
	}
}
