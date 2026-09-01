import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWoodall {

	public static Boolean isWoodall(int x) {
		if (x % 2 == 0) {
			return false;
		}

		if (x == 1) {
			return true;
		}

		int p = 0;

		x++;
		while (x % 2 == 0) {

			p++;

			x = x / 2;

			if (p == x) {
				return true;
			}
		}

		return false;
	}
}
