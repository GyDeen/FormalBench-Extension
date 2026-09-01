
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EmptyList {

	public static HashMap<Object, Object>[] emptyList(int length) {
		HashMap<Object, Object>[] array = new HashMap[length];
		for (int i = 0; length > i; i++) {
			array[i] = new HashMap<>();
		}
		return array;
	}
}
