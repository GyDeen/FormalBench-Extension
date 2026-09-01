import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SuperSeq {

	public static int superSeq(String string, String b, int z, int p) {
		if (z == 0) {
			return p;
		}
		if (p == 0) {
			return z;
		}
		if (string.charAt(z - 1) == b.charAt(p - 1)) {
			return 1 + superSeq(string, b, z - 1, p - 1);
		}
		return 1 + Math.min(superSeq(string, b, z - 1, p),
				superSeq(string, b, z, p - 1));
	}
}
