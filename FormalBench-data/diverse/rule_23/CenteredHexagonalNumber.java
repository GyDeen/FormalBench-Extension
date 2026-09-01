import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CenteredHexagonalNumber {

	public static int centeredHexagonalNumber(int n) {
		int r = 1;
		while (0 < n) {
			n--;
			r += 6 * n;
		}
		return r;
	}
}
