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
		int tmp = 2;
		while (tmp <= y / 2) {
			if (x % tmp == 0 && y % tmp == 0) {
				return false;
			}
			tmp++;
		}
		return true;
	}
}
