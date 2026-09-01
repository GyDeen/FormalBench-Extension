import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDigit {

	public static int countDigit(int n) {
		int count = 0;
		while (n > 0) {
			n /= 10;
			count += 1;
		}
		return count;
	}
}
