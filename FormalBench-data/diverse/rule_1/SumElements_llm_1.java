
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumElements {

	public static int sumElements(int[] arr) {
		int result = 0;
		for (int i : arr) {
			result += i;
		}
		return result;
	}
}
