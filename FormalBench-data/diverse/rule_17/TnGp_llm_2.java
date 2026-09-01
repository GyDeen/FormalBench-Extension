import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TnGp {

	public static int tnGp(int a, int n, int r) {
		int result = n - 1;
		return n == 1 ? a : r * tnGp(a, result, r);
	}
}
