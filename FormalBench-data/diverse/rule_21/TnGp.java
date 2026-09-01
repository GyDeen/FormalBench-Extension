import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TnGp {

	public static int tnGp(int a, int n, int r) {
		return 1 == n ? a : r * tnGp(a, n - 1, r);
	}
}
