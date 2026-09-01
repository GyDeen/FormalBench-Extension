import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumCommDiv {

	public static int numCommDiv(int x, int y) {
		int count = 0;
		int result = 1;
		while (result <= x && result <= y) {
			if (x % result == 0 && y % result == 0) {
				count++;
			}
			result++;
		}
		return count;
	}
}
