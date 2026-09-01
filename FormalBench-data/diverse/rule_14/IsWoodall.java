import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWoodall {

	public static Boolean isWoodall(int x) {
		if (!(x % 2 == 0))
			;
		else {
			return false;
		}

		if (!(x == 1))
			;
		else {
			return true;
		}

		x++;

		int p = 0;
		while (x % 2 == 0) {

			x = x / 2;

			p++;

			if (!(p == x))
				;
			else {
				return true;
			}
		}

		return false;
	}
}
