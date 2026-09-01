import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifSquare {

	public static Boolean difSquare(int n) {
		while (n > 0) {
			if (n % 10 == 0) {
				return false;
			}
			if (n % 10 == 1) {
				return true;
			}
			n = n / 10;
		}
		return true;
	}
}
