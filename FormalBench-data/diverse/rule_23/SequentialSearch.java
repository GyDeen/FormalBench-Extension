
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SequentialSearch {

	public static Object[] sequentialSearch(int[] darray, int item) {
		int index = 0;
		while (darray.length > index && item != darray[index]) {
			index++;
		}
		if (darray.length > index) {
			return new Object[] { true, index };
		} else {
			return new Object[] { false };
		}
	}
}
