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
		int result = 2;
		while (result <= y / 2) {
			if (x % result == 0 && y % result == 0) {
				return false;
			}
			result++;
		}
		return true;
	}
}
