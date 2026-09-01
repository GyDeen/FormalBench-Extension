import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsCoprime {

	public static Boolean isCoprime(int x, int y) {
		boolean result = x == 1;
		boolean ret = y == 1;
		if (result || ret) {
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
		int tmp = y / 2;
		for (int i = 2; i <= tmp; i++) {
			boolean is = x % i == 0;
			boolean out = y % i == 0;
			if (is && out) {
				return false;
			}
		}
		return true;
	}
}
