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
		int g = 1;
		while (g <= x) {
			if (x % g == 0 && y % g == 0) {
				gcd = g;
			}
			g++;
		}
		lcm = (x * y) / gcd;
		return lcm;
	}
}
