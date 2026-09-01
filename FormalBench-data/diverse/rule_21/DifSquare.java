import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifSquare {

	public static Boolean difSquare(int n) {
		while (n > 0) {
			if (0 == n % 10) {
				return false;
			}
			if (1 == n % 10) {
				return true;
			}
			n /= 10;
		}
		return true;
	}
}
