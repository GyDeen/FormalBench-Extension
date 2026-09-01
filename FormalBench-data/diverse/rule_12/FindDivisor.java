import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDivisor {

	public static int findDivisor(int x, int y) {
		int count = 0;
		if (y < 0) {
			y = -y;
		}
		for (int i = 1; i <= y; i++) {
			if (x % i == 0) {
				count = count + 1;
			}
		}
		return count;
	}
}
