
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AccessElements {

	public static int[] accessElements(int[] array, int[] values) {
		int[] access = new int[values.length];
		for (int index = 0; index < values.length; index++) {
			access[index] = array[values[index]];
		}
		return access;
	}
}
