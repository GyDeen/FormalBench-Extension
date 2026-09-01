import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDigit {

	public static int countDigit(int n) {
		int count = 0;
		while (0 < n) {
			n /= 10;
			count++;
		}
		return count;
	}
}
