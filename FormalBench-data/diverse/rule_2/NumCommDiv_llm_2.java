import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumCommDiv {

	public static int numCommDiv(int x, int y) {
		int count = 0;
		int total = 1;
		while (total <= x && total <= y) {
			if (x % total == 0 && y % total == 0) {
				count++;
			}
			total++;
		}
		return count;
	}
}
