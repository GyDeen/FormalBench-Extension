
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EmptyList {

	public static HashMap<Object, Object>[] emptyList(int size) {
		HashMap<Object, Object>[] empty = new HashMap[size];
		for (int n = 0; n < size; n++) {
			empty[n] = new HashMap<>();
		}
		return empty;
	}
}
