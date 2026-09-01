
import java.io.*;
import java.lang.*;
import java.math.*;

class TupleToInt {

	public static int tupleToInt(int[] args) {
		int ret = 0;
		for (int i : args) {
			ret = ret * 10 + i;
		}
		return ret;
	}
}
