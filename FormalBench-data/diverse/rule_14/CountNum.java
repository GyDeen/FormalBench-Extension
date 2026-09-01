import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNum {

	public static int countNum(int n) {
		int count = 0;
		while (n > 0) {
			if (!((n & 1) == 1))
				;
			else {
				count++;
			}
			n = n >> 1;
		}
		return count;
	}
}
