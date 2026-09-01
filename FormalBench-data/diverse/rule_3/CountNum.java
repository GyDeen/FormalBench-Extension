import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNum {

	public static int countNum(int n) {
		int count = 0;
		for (; n > 0;) {
			if ((n & 1) == 1) {
				count++;
			}
			n = n >> 1;
		}
		return count;
	}
}
