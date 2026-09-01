import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDigit {

	public static int countDigit(int n) {
		int count = 0;
		for (; n > 0;) {
			n /= 10;
			count++;
		}
		return count;
	}
}
