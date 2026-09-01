
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumElements {

	public static int sumElements(int[] elements) {
		int result = 0;
		for (int i : elements) {
			result += i;
		}
		return result;
	}
}
