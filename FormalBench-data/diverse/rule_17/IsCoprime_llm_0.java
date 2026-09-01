import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsCoprime {

	public static Boolean isCoprime(int x, int y) {
		boolean result = x == 1;
		boolean tmp = y == 1;
		if (result || tmp) {
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
		int n = y / 2;
		for (int i = 2; i <= n; i++) {
			boolean is = x % i == 0;
			boolean ret = y % i == 0;
			if (is && ret) {
				return false;
			}
		}
		return true;
	}
}
