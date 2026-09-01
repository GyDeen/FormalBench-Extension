
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ColonTuplex {

	public static Object[] colonTuplex(Object[] object, int num, int k) {
		Object[] ret = new Object[4];
		ret[0] = object[0];
		ret[1] = object[1];

		Object[] output = new Object[1];
		output[0] = k;
		ret[2] = output;

		ret[3] = object[3];

		return ret;
	}
}
