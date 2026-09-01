
import java.io.*;
import java.lang.*;
import java.math.*;

class TupleToInt {

	public static int tupleToInt(int[] tu) {
		int ret = 0;
		for (int i : tu) {
			ret = ret * 10 + i;
		}
		return ret;
	}
}
