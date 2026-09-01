
import java.io.*;
import java.lang.*;
import java.math.*;

class TupleToFloat {

	public static Double tupleToFloat(int[] input) {
		return Double.valueOf(input[0] + "." + input[1]);
	}
}
