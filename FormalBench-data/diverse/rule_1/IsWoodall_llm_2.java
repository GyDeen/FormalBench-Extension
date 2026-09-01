import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWoodall {

	public static Boolean isWoodall(int n) {
		if (n % 2 == 0) {
			return false;
		}

		if (n == 1) {
			return true;
		}

		n++;

		int y = 0;
		while (n % 2 == 0) {

			n = n / 2;

			y++;

			if (y == n) {
				return true;
			}
		}

		return false;
	}
}
