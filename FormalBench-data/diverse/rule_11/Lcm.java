import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Lcm {

	public static int lcm(int x, int y) {
		int gcd = 1, lcm = 1;
		if (x > y) {
			int temp = x;
			x = y;
			y = temp;
		}
		for (int i = 1; i <= x; i++) {
			if (x % i == 0 && y % i == 0) {
				gcd = i;
			}
		}
		lcm = (x * y) / gcd;
		return lcm;
	}
}
