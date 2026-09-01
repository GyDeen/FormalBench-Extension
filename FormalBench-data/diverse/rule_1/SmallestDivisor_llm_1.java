import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestDivisor {

	public static int smallestDivisor(int size) {
		int result = 2;
		while (result <= size / result) {
			if (size % result == 0) {
				return result;
			}
			result++;
		}
		return size;
	}
}
