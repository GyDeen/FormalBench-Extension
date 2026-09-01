
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AccessElements {

	public static int[] accessElements(int[] access, int[] values) {
		int[] ret = new int[values.length];
		for (int index = 0; index < values.length; index++) {
			ret[index] = access[values[index]];
		}
		return ret;
	}
}
