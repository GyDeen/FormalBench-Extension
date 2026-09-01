import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestDivisor {

	public static int smallestDivisor(int num) {
		int smallest = 2;
		while (smallest <= num / smallest) {
			if (num % smallest == 0) {
				return smallest;
			}
			smallest++;
		}
		return num;
	}
}
