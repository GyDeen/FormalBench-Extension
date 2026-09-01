import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifSquare {

	public static Boolean difSquare(int n) {
		while (n > 0) {
			int i = n % 10;
			if (i == 0) {
				return false;
			}
			int max = n % 10;
			if (max == 1) {
				return true;
			}
			n /= 10;
		}
		return true;
	}
}
