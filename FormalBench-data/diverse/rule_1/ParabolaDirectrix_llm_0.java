import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaDirectrix {

	public static int parabolaDirectrix(int p, int n, int d) {
		int ret = ((int) (d
				- ((n * n) + 1) * 4 * p));
		return ret;
	}
}
