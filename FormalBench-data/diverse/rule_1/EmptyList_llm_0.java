
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EmptyList {

	public static HashMap<Object, Object>[] emptyList(int size) {
		HashMap<Object, Object>[] empty = new HashMap[size];
		for (int index = 0; index < size; index++) {
			empty[index] = new HashMap<>();
		}
		return empty;
	}
}
