import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TnAp {

	public static int tnAp(int a, int n, int d) {
		int ret = (n - 1) * d;
		return a + ret;
	}
}
