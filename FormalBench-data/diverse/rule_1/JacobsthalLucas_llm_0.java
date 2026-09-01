import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class JacobsthalLucas {

	public static int jacobsthalLucas(int c) {
		int j[] = new int[c + 1];
		j[0] = 2;
		j[1] = 1;
		for (int p = 2; p <= c; p++) {
			j[p] = j[p - 1]
					+ 2 * j[p - 2];
		}
		return j[c];
	}
}
