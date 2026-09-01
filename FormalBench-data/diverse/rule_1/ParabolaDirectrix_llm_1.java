import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaDirectrix {

	public static int parabolaDirectrix(int size, int n, int d) {
		int ret = ((int) (d
				- ((n * n) + 1) * 4 * size));
		return ret;
	}
}
