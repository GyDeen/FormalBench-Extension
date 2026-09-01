
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AccessElements {

	public static int[] accessElements(int[] access, int[] values) {
		int[] ret = new int[values.length];
		for (int id = 0; id < values.length; id++) {
			ret[id] = access[values[id]];
		}
		return ret;
	}
}
