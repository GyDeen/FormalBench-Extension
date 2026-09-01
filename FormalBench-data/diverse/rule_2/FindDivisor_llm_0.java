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
		int result = 1;
		while (result <= y) {
			if (x % result == 0) {
				count = count + 1;
			}
			result++;
		}
		return count;
	}
}
