
import java.io.*;
import java.lang.*;
import java.util.*;

class RearExtract {

	public static int[] rearExtract(Object[][] array) {
		int[] ret = new int[array.length];

		for (int index = 0; index < array.length; index++) {
			ret[index] = (Integer) array[index][2];
		}

		return ret;
	}
}
