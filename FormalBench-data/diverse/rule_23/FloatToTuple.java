
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloatToTuple {

	public static double[] floatToTuple(String testStr) {
		String[] strArr = testStr.split(",");
		double[] result = new double[strArr.length];
		for (int i = 0; strArr.length > i; i++) {
			result[i] = Double.parseDouble(strArr[i].trim());
		}
		return result;
	}
}
