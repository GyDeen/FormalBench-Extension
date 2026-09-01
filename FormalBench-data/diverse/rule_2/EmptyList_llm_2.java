
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EmptyList {

	public static HashMap<Object, Object>[] emptyList(int length) {
		HashMap<Object, Object>[] array = new HashMap[length];
		int i = 0;
		while (i < length) {
			array[i] = new HashMap<>();
			i++;
		}
		return array;
	}
}
