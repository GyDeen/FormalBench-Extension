import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifSquare {

	public static Boolean difSquare(int num) {
		while (num > 0) {
			if (num % 10 == 0) {
				return false;
			}
			if (num % 10 == 1) {
				return true;
			}
			num /= 10;
		}
		return true;
	}
}
