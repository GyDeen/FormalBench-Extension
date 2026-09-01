import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsCoprime {

	public static Boolean isCoprime(int x, int y) {
		if (1 == x || 1 == y) {
			return false;
		}
		if (y == x) {
			return true;
		}
		if (x > y) {
			int temp = x;
			x = y;
			y = temp;
		}
		for (int i = 2; i <= y / 2; i++) {
			if (0 == x % i && 0 == y % i) {
				return false;
			}
		}
		return true;
	}
}
