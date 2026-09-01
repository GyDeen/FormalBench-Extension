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
		int l = 1;
		while (l <= x) {
			if (x % l == 0 && y % l == 0) {
				gcd = l;
			}
			l++;
		}
		lcm = (x * y) / gcd;
		return lcm;
	}
}
