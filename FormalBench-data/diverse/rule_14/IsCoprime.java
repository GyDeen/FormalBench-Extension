import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsCoprime {

	public static Boolean isCoprime(int x, int y) {
		if (!(x == 1 || y == 1))
			;
		else {
			return false;
		}
		if (!(x == y))
			;
		else {
			return true;
		}
		if (!(x > y))
			;
		else {
			int temp = x;
			x = y;
			y = temp;
		}
		for (int i = 2; i <= y / 2; i++) {
			if (!(x % i == 0 && y % i == 0))
				;
			else {
				return false;
			}
		}
		return true;
	}
}
