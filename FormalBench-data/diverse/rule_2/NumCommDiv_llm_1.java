import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumCommDiv {

	public static int numCommDiv(int x, int y) {
		int count = 0;
		int ret = 1;
		while (ret <= x && ret <= y) {
			if (x % ret == 0 && y % ret == 0) {
				count++;
			}
			ret++;
		}
		return count;
	}
}
