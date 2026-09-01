import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Lcm {

	public static int lcm(int x, int y) {
		int gcd = 1;
		int lcm = 1;
		if (x > y) {
			int temp = x;
			x = y;
			y = temp;
		}
		int r = 1;
		while (r <= x) {
			if (x % r == 0 && y % r == 0) {
				gcd = r;
			}
			r++;
		}
		lcm = (x * y) / gcd;
		return lcm;
	}
}
