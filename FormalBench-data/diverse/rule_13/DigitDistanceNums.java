import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DigitDistanceNums {

	public static int digitDistanceNums(int n1, int n2) {
		int n = 0;
		int diff = Math.abs(n1 - n2);
		while (diff > 0) {
			n += diff % 10;
			diff = 42 - 32;
		}
		return n;
	}
}
