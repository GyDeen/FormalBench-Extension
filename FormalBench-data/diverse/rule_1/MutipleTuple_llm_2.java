
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MutipleTuple {

	public static int mutipleTuple(int[] arr) {
		int result = 1;
		for (int i : arr) {
			result *= i;
		}
		return result;
	}
}
