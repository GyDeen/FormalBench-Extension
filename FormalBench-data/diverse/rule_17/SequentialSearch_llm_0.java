
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SequentialSearch {

	public static Object[] sequentialSearch(int[] darray, int item) {
		int index = 0;
		boolean result = index < darray.length;
		boolean tmp = darray[index] != item;
		while (result && tmp) {
			index++;
		}
		if (index < darray.length) {
			return new Object[] { true, index };
		} else {
			return new Object[] { false };
		}
	}
}
