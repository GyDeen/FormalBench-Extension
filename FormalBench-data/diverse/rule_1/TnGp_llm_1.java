import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TnGp {

	public static int tnGp(int num, int b, int p) {
		return b == 1 ? num
				: p * tnGp(num, b - 1, p);
	}
}
