import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWoodall {

	public static Boolean isWoodall(int x) {
		int r = x % 2;
		if (r == 0) {
			return false;
		}

		if (x == 1) {
			return true;
		}

		x++;

		int p = 0;
		int c = x % 2;
		while (c == 0) {

			x = x / 2;

			p++;

			if (p == x) {
				return true;
			}
		}

		return false;
	}
}
