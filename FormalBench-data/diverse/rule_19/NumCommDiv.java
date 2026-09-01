import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumCommDiv {

	public static int numCommDiv(int x, int y) {
		int count = 0;
		for (int i = 1; i <= x && i <= y; i++) {
			if (x % i == 0) {
				if (y % i == 0) {
					count++;
				}
			}
		}
		return count;
	}
}
