
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleModulo {

	public static int[] tupleModulo(int[] array, int[] test) {
		int num = array.length;
		int[] tmp = new int[num];
		for (int index = 0; index < num; index++) {
			tmp[index] = array[index]
					% test[index];
		}
		return tmp;
	}
}
