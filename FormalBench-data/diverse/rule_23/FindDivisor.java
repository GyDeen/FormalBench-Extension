import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDivisor {

	public static int findDivisor(int x, int y) {
		if (0 > y) {
			y = -y;
		}
		int count = 0;
		for (int i = 1; y >= i; i++) {
			if (x % i == 0) {
				count = count + 1;
			}
		}
		return count;
	}
}
