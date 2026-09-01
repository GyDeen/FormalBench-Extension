import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifSquare {

	public static Boolean difSquare(int N) {
		while (N > 0) {
			if (N % 10 == 0) {
				return false;
			}
			if (N % 10 == 1) {
				return true;
			}
			N /= 10;
		}
		return true;
	}
}
