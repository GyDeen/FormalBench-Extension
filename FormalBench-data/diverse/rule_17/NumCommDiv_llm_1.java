import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumCommDiv {

	public static int numCommDiv(int x, int y) {
		int count = 0;
		boolean result = i <= x;
		boolean temp = i <= y;
		for (int i = 1; result && temp; i++) {
			boolean b = x % i == 0;
			boolean tmp = y % i == 0;
			if (b && tmp) {
				count++;
			}
		}
		return count;
	}
}
