import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LastTwoDigits {

	public static int lastTwoDigits(int n) {
		int factorial = 1;
		int number = 1;
		while (number <= n) {
			factorial *= number;
			number++;
		}
		int lastTwoDigits = (int) (factorial % 100);
		return lastTwoDigits;
	}
}
