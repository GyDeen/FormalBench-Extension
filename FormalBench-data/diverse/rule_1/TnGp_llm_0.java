import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TnGp {

	public static int tnGp(int num, int c, int b) {
		return c == 1 ? num
				: b * tnGp(num, c - 1, b);
	}
}
