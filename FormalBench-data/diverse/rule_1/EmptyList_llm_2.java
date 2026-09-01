
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EmptyList {

	public static HashMap<Object, Object>[] emptyList(int n) {
		HashMap<Object, Object>[] empty = new HashMap[n];
		for (int index = 0; index < n; index++) {
			empty[index] = new HashMap<>();
		}
		return empty;
	}
}
