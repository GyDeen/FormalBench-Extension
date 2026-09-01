import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumCommDiv {

	public static int numCommDiv(int x, int y) {
		int count = 0;
		boolean b = i <= x;
		boolean temp = i <= y;
		for (int i = 1; b && temp; i++) {
			boolean result = x % i == 0;
			boolean tmp = y % i == 0;
			if (result && tmp) {
				count++;
			}
		}
		return count;
	}
}
