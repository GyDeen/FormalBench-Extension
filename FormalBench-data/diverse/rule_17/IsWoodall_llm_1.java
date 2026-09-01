import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWoodall {

	public static Boolean isWoodall(int x) {
		int i = x % 2;
		if (i == 0) {
			return false;
		}

		if (x == 1) {
			return true;
		}

		x++;

		int p = 0;
		int n = x % 2;
		while (n == 0) {

			x = x / 2;

			p++;

			if (p == x) {
				return true;
			}
		}

		return false;
	}
}
