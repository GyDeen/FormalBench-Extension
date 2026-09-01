import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsCoprime {

	public static Boolean isCoprime(int x, int y) {
		if (x == 1 || y == 1) {
			return false;
		}
		if (x == y) {
			return true;
		}
		if (x > y) {
			int temp = x;
			x = y;
			y = temp;
		}
		int n = 2;
		while (n <= y / 2) {
			if (x % n == 0 && y % n == 0) {
				return false;
			}
			n++;
		}
		return true;
	}
}
