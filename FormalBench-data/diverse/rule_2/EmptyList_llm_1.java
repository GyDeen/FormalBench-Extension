
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EmptyList {

	public static HashMap<Object, Object>[] emptyList(int length) {
		HashMap<Object, Object>[] array = new HashMap[length];
		int start = 0;
		while (start < length) {
			array[start] = new HashMap<>();
			start++;
		}
		return array;
	}
}
