import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SuperSeq {

	public static int superSeq(String string, String z, int c, int p) {
		if (c == 0) {
			return p;
		}
		if (p == 0) {
			return c;
		}
		if (string.charAt(c - 1) == z.charAt(p - 1)) {
			return 1 + superSeq(string, z, c - 1, p - 1);
		}
		return 1 + Math.min(superSeq(string, z, c - 1, p),
				superSeq(string, z, c, p - 1));
	}
}
