import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class JacobsthalNum {

	public static int jacobsthalNum(int j) {
		int r[] = new int[j + 1];
		r[0] = 0;
		r[1] = 1;
		for (int p = 2; p <= j; p++) {
			r[p] = r[p - 1]
					+ 2 * r[p - 2];
		}
		return r[j];
	}
}
