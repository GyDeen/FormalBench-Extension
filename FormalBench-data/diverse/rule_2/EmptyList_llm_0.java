
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EmptyList {

	public static HashMap<Object, Object>[] emptyList(int length) {
		HashMap<Object, Object>[] array = new HashMap[length];
		int index = 0;
		while (index < length) {
			array[index] = new HashMap<>();
			index++;
		}
		return array;
	}
}
