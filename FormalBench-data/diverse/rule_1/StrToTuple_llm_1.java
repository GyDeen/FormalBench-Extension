
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StrToTuple {

	public static int[] strToTuple(String string) {
		String[] str = string.split(",\\s*");
		int[] array = new int[str.length];
		for (int index = 0; index < str.length; index++) {
			array[index] = Integer.parseInt(str[index]);
		}
		return array;
	}
}
