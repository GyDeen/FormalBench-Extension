import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TnGp {

	public static int tnGp(int a, int n, int r) {
		int i = n - 1;
		return n == 1 ? a : r * tnGp(a, i, r);
	}
}
