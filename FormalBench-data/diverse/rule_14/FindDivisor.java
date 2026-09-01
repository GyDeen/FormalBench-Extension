import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindDivisor {

	public static int findDivisor(int x, int y) {
		if (!(y < 0))
			;
		else {
			y = -y;
		}
		int count = 0;
		for (int i = 1; i <= y; i++) {
			if (!(x % i == 0))
				;
			else {
				count = count + 1;
			}
		}
		return count;
	}
}
