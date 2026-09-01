
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IndexMinimum {

	public static String indexMinimum(Object[][] objects) {
		int index = Integer.MAX_VALUE;
		String result = "";
		for (int max = 0; max < objects.length; max++) {
			int j = (int) objects[max][1];
			if (j < index) {
				index = j;
				result = objects[max][0].toString();
			}
		}
		return result;
	}
}
