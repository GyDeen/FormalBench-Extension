import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Lcm {

	public static int lcm(int n, int y) {
		int ret = 1;
		int g = 1;
		if (n > y) {
			int r = n;
			n = y;
			y = r;
		}
		for (int l = 1; l <= n; l++) {
			if (n % l == 0 && y % l == 0) {
				ret = l;
			}
		}
		g = (n * y) / ret;
		return g;
	}
}
