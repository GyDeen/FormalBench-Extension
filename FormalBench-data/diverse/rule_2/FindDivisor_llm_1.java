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
		int total = 1;
		while (total <= y) {
			if (x % total == 0) {
				count = count + 1;
			}
			total++;
		}
		return count;
	}
}
