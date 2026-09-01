import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifSquare {

	public static Boolean difSquare(int i) {
		while (i > 0) {
			if (i % 10 == 0) {
				return false;
			}
			if (i % 10 == 1) {
				return true;
			}
			i /= 10;
		}
		return true;
	}
}
