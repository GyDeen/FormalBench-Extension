import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestDivisor {

	public static int smallestDivisor(int size) {
		int smallest = 2;
		while (smallest <= size / smallest) {
			if (size % smallest == 0) {
				return smallest;
			}
			smallest++;
		}
		return size;
	}
}
