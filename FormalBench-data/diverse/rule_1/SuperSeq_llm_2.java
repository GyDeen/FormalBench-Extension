import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SuperSeq {

	public static int superSeq(String str, String z, int c, int p) {
		if (c == 0) {
			return p;
		}
		if (p == 0) {
			return c;
		}
		if (str.charAt(c - 1) == z.charAt(p - 1)) {
			return 1 + superSeq(str, z, c - 1, p - 1);
		}
		return 1 + Math.min(superSeq(str, z, c - 1, p),
				superSeq(str, z, c, p - 1));
	}
}
