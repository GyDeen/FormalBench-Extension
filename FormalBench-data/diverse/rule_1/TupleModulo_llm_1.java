
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleModulo {

	public static int[] tupleModulo(int[] input, int[] test) {
		int num = input.length;
		int[] tmp = new int[num];
		for (int index = 0; index < num; index++) {
			tmp[index] = input[index]
					% test[index];
		}
		return tmp;
	}
}
