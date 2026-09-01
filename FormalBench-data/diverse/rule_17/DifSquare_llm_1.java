import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifSquare {

	public static Boolean difSquare(int n) {
		while (n > 0) {
			int ret = n % 10;
			if (ret == 0) {
				return false;
			}
			int tmp = n % 10;
			if (tmp == 1) {
				return true;
			}
			n /= 10;
		}
		return true;
	}
}
