import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Lcm {

	public static int lcm(int x, int y) {
		int gcd = 1;
		int lcm = 1;
		if (!(x > y))
			;
		else {
			int temp = x;
			x = y;
			y = temp;
		}
		for (int i = 1; i <= x; i++) {
			if (!(x % i == 0 && y % i == 0))
				;
			else {
				gcd = i;
			}
		}
		lcm = (x * y) / gcd;
		return lcm;
	}
}
