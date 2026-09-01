import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDivisor {

	public static int findDivisor(int x, int y) {
		y = (y < 0) ? -y : y;
		int count = 0;
		for (int i = 1; i <= y; i++) {
			count = (x % i == 0) ? count + 1 : count;
		}
		return count;
	}
}
