
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SequentialSearch {

	public static Object[] sequentialSearch(int[] darray, int item) {
		int index = 0;
		while (index < darray.length && darray[index] != item) {
			index++;
		}
		if (!(index < darray.length)) {
			return new Object[] { false };
		} else {
			return new Object[] { true, index };
		}
	}
}
