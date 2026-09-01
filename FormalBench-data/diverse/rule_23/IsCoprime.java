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
		if (y < x) {
			int temp = x;
			x = y;
			y = temp;
		}
		for (int i = 2; y / 2 >= i; i++) {
			if (x % i == 0 && y % i == 0) {
				return false;
			}
		}
		return true;
	}
}
