import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDivisor {

	public static int findDivisor(int x, int y) {
		if (y < 0) {
			y = -y;
		}
		int count = 0;
		for (int i = 1; i <= y; i++) {
			int tmp = x % i;
			if (tmp == 0) {
				count = count + 1;
			}
		}
		return count;
	}
}
