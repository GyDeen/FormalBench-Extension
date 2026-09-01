import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWoodall {

	public static Boolean isWoodall(int x) {
		if (0 == x % 2) {
			return false;
		}

		if (1 == x) {
			return true;
		}

		x++;

		int p = 0;
		while (0 == x % 2) {

			x = x / 2;

			p++;

			if (x == p) {
				return true;
			}
		}

		return false;
	}
}
