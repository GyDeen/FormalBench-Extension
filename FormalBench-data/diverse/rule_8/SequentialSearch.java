
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SequentialSearch {

	public static Object[] sequentialSearch(int[] darray, int item) {
		int index = 0;
		while (index < darray.length && darray[index] != item) {
			index += 1;
		}
		if (index < darray.length) {
			return new Object[] { true, index };
		} else {
			return new Object[] { false };
		}
	}
}
