
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ColonTuplex {

	public static Object[] colonTuplex(Object[] tuplex, int m, int n) {
		Object[] result = new Object[4];
		result[0] = tuplex[0];
		Object[] nArray = new Object[1];

		result[1] = tuplex[1];
		nArray[0] = n;
		result[2] = nArray;

		result[3] = tuplex[3];

		return result;
	}
}
