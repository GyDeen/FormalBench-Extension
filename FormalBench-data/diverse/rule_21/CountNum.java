import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNum {

	public static int countNum(int n) {
		int count = 0;
		while (n > 0) {
			if (1 == (n & 1)) {
				count++;
			}
			n = n >> 1;
		}
		return count;
	}
}
