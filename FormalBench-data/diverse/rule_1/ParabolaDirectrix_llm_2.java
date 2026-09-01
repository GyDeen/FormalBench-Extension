import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaDirectrix {

	public static int parabolaDirectrix(int p, int n, int C) {
		int ret = ((int) (C
				- ((n * n) + 1) * 4 * p));
		return ret;
	}
}
