import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TnGp {

	public static int tnGp(int p, int c, int b) {
		return c == 1 ? p
				: b * tnGp(p, c - 1, b);
	}
}
