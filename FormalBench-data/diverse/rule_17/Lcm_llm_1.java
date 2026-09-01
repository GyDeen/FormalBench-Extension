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
		for (int i = 1; i <= x; i++) {
			boolean b = x % i == 0;
			boolean tmp = y % i == 0;
			if (b && tmp) {
				gcd = i;
			}
		}
		int g = x * y;
		lcm = (g) / gcd;
		return lcm;
	}
}
